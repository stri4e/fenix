package handlers

import (
	"../controllers"
	"../dto"
	log "../logger"
	"encoding/json"
	"github.com/gorilla/mux"
	"net/http"
	"strconv"
)

type LoginHandler struct {
	controller *controllers.LoginsController
}

func NewLoginHandler(controller *controllers.LoginsController) *LoginHandler {
	return &LoginHandler{controller: controller}
}

func (handler *LoginHandler) GetByAccountId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["accountId"]
	accountId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	login, err := handler.controller.GetByAccountId(uint(accountId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: read all account information by account id.")
	ResponseSender(w, login, http.StatusOK)
}

func (handler *LoginHandler) CreateLogin(w http.ResponseWriter, r *http.Request) {
	var payload dto.LoginDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	login, err := handler.controller.CreateLogin(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new  user login information")
	ResponseSender(w, login, http.StatusCreated)
}
