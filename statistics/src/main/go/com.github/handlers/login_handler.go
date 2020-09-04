package handlers

import (
	"../controllers"
	"../dto"
	"../utils"
	"encoding/json"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strconv"
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
			result := vars["userId"]
			userId, err := strconv.ParseUint(result, BaseUint, BitSize)
			utils.ThrowIfErr(err, &utils.BadRequest{Message: "Arguments must be a number."})
			logins, err := handler.controller.FindByUserId(uint(userId))
			utils.ThrowIfErr(err, &utils.NotFound{Message: "Logins not found."})
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
			utils.ThrowIfNil(start, &utils.BadRequest{Message: "Request path is required."})
			utils.ThrowIfNil(end, &utils.BadRequest{Message: "Request path is required."})
			login, err := handler.controller.FindBetweenTime(start, end)
			utils.ThrowIfErr(err, &utils.NotFound{Message: "Logins not found."})
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
			utils.ThrowIfErr(err, &utils.BadRequest{Message: "Can't deserialize LoginDto."})
			login, err := handler.controller.CreateLogin(&payload)
			utils.ThrowIfErr(err, &utils.BadRequest{Message: "Can't save LoginDto."})
			log.WithFields(log.Fields{"Device": payload.Device, "Location": payload.Location}).
				Debug("Enter: create new  user login information")
			ResponseSender(w, login, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
