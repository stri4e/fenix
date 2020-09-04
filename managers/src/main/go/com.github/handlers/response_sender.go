package handlers

import (
	"../models"
	"encoding/json"
	log "github.com/sirupsen/logrus"
	"net/http"
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
	if err != nil {
		log.Error("Enter: ", code, err)
	}
}

func ErrorSender(w http.ResponseWriter, code int, message string) {
	e := models.Error{Code: code, Message: message}
	err, _ := json.Marshal(e)
	http.Error(w, string(err), code)
}
