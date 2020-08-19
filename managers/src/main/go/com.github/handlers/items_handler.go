package handlers

import (
	"../controllers"
	"../dto"
	log "../logger"
	"../utils"
	"encoding/json"
	"github.com/gorilla/mux"
	"net/http"
	"strconv"
)

type ItemsHandler struct {
	controller *controllers.ItemsController
}

func NewItemsHandler(controller *controllers.ItemsController) *ItemsHandler {
	return &ItemsHandler{controller: controller}
}

func (handler *ItemsHandler) SaveItem(w http.ResponseWriter, r *http.Request) {
	tokenHeader := r.Header.Get("Authorization")
	if tokenHeader == "" {
		http.Error(w, "", http.StatusForbidden)
		return
	}
	token, err := utils.GetToken(tokenHeader)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	managerId, err := strconv.ParseUint(token.Subject, 10, 64)
	var payload dto.ItemDto
	err = json.NewDecoder(r.Body).Decode(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	err = handler.controller.SaveItem(uint(managerId), token.FirstName, token.LastName, &payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new  manger orders information")
	ResponseSender(w, "", http.StatusCreated)
}

func (handler *ItemsHandler) FindItemsByStatus(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	status := vars["status"]
	if utils.IsBlank(status) {
		http.Error(w, "Request path is required.", http.StatusBadRequest)
	}
	tokenHeader := r.Header.Get("Authorization")
	if tokenHeader == "" {
		http.Error(w, "", http.StatusForbidden)
		return
	}
	managerId, err := utils.GetSubject(tokenHeader)
	payload, err := handler.controller.FindItems(managerId, status)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: find orders information")
	ResponseSender(w, payload, http.StatusOK)
}

func (handler *ItemsHandler) UpdateStatusItem(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	orderIdStr := vars["orderId"]
	status := vars["status"]
	if utils.IsBlank(orderIdStr) || utils.IsBlank(status) {
		http.Error(w, "Request paths is required.", http.StatusBadRequest)
	}
	orderId, err := strconv.ParseUint(orderIdStr, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	err = handler.controller.UpdateStatusItem(uint(orderId), status)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: update orders information")
	ResponseSender(w, "", http.StatusOK)
}
