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

type AccountsHandler struct {
	controller *controllers.AccountsController
}

func NewAccountsHandler(controller *controllers.AccountsController) *AccountsHandler {
	return &AccountsHandler{controller: controller}
}

func (handler *AccountsHandler) GetByUserId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["userId"]
	userId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	account, err := handler.controller.GetByUserId(uint(userId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: read account information success ")
	ResponseSender(w, account, http.StatusOK)
}

func (handler *AccountsHandler) CreateAccount(w http.ResponseWriter, r *http.Request) {
	var payload dto.AccountDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	account, err := handler.controller.CreateAccount(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new account success")
	ResponseSender(w, account, http.StatusCreated)
}
