package handlers

import (
	"encoding/json"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"net/http"
	"statistics/src/main/go/com.github/controllers"
	"statistics/src/main/go/com.github/dto"
	"statistics/src/main/go/com.github/utils"
)

type LoginHandler struct {
	controller *controllers.LoginsController
}

func NewLoginHandler(controller *controllers.LoginsController) *LoginHandler {
	return &LoginHandler{controller: controller}
}

func (handler *LoginHandler) FindByUserId(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			userId := vars["userId"]
			utils.ThrowIfNil(userId, http.StatusBadRequest, "Request path is required.")
			logins, err := handler.controller.FindByUserId(userId)
			utils.ThrowIfErr(err, http.StatusNotFound, "Logins not found.")
			ResponseSender(w, logins, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *LoginHandler) FindBetweenTime(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			start := r.FormValue("start")
			end := r.FormValue("end")
			utils.ThrowIfNil(start, http.StatusBadRequest, "Request path is required.")
			utils.ThrowIfNil(end, http.StatusBadRequest, "Request path is required.")
			login, err := handler.controller.FindBetweenTime(start, end)
			utils.ThrowIfErr(err, http.StatusNotFound, "Logins not found.")
			log.WithFields(log.Fields{"start": start, "end": end}).
				Debug("Enter: read all account information", start, end)
			ResponseSender(w, login, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *LoginHandler) CreateLogin(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.LoginDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize LoginDto.")
			login, err := handler.controller.CreateLogin(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save LoginDto.")
			log.WithFields(log.Fields{"Device": payload.Device, "Location": payload.Location}).
				Debug("Enter: create new  user login information")
			ResponseSender(w, login, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
