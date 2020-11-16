package handlers

import (
	"encoding/json"
	log "github.com/sirupsen/logrus"
	"net/http"
	"statistics/src/main/go/com.github/models"
)

const (
	ContentType     = "Content-Type"
	ApplicationJson = "application/json"
	BaseUint        = 10
	BitSize         = 64
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
	e := elem.(models.Error)
	code := e.Code
	err, _ := json.Marshal(e)
	http.Error(w, string(err), code)
	log.WithFields(log.Fields{"Error": e}).
		Warn("Enter: send response")
}
