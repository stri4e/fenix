package handlers

import (
	"../models"
	"../utils"
	"encoding/json"
	log "github.com/sirupsen/logrus"
	"net/http"
	"reflect"
)

const (
	ContentType     = "Content-Type"
	ApplicationJson = "application/json"
)

func ResponseSender(w http.ResponseWriter, payload interface{}, status int) {
	response, err := json.Marshal(payload)
	w.Header().Set(ContentType, ApplicationJson)
	w.WriteHeader(status)
	code, err := w.Write(response)
	log.WithFields(log.Fields{"payload": string(response)}).Debug("Enter: send response")
	if err != nil {
		log.Error("Enter: ", code, err)
	}
}

func ErrorSender(w http.ResponseWriter, elem interface{}) {
	var code int
	var e models.Error
	switch reflect.TypeOf(elem).Name() {
	case "NotFound":
		code = http.StatusNotFound
		exception := elem.(utils.NotFound)
		e = models.Error{Code: code, Message: exception.Message}
		break
	case "BadRequest":
		code = http.StatusBadRequest
		exception := elem.(utils.BadRequest)
		e = models.Error{Code: code, Message: exception.Message}
		break
	default:
		code = http.StatusBadRequest
		break
	}
	err, _ := json.Marshal(e)
	http.Error(w, string(err), code)
	log.WithFields(log.Fields{"Code": code, "Error": err}).
		Warn("Enter: send response")
}
