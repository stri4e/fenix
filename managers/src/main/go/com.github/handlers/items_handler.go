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
		ErrorSender(w, http.StatusForbidden, "Authorization token not fount.")
		return
	}
	token := utils.GetToken(tokenHeader)
	if token == nil {
		ErrorSender(w, http.StatusBadRequest, "Can't authorize")
		return
	}
	managerId, err := strconv.ParseUint(token.Subject, 10, 64)
	var payload dto.ItemDto
	err = json.NewDecoder(r.Body).Decode(&payload)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't deserialize Payload.")
		return
	}
	err = handler.controller.SaveItem(uint(managerId), token.FirstName, token.LastName, &payload)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't save items.")
		return
	}
	log.Debug("Enter: create new  manger orders information")
	ResponseSender(w, "", http.StatusCreated)
}

func (handler *ItemsHandler) FindItemsByStatus(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	status := vars["status"]
	if utils.IsBlank(status) {
		ErrorSender(w, http.StatusBadRequest, "Request path is required.")
		return
	}
	tokenHeader := r.Header.Get("Authorization")
	if tokenHeader == "" {
		ErrorSender(w, http.StatusBadRequest, "Can't authorize")
		return
	}
	managerId, err := utils.GetSubject(tokenHeader)
	payload, err := handler.controller.FindItems(managerId, status)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Items not found")
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
		ErrorSender(w, http.StatusBadRequest, "Request path is required.")
		return
	}
	orderId, err := strconv.ParseUint(orderIdStr, 10, 64)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Order id is not number.")
		return
	}
	err = handler.controller.UpdateStatusItem(uint(orderId), status)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't update order.")
		return
	}
	log.Debug("Enter: update orders information")
	ResponseSender(w, "", http.StatusOK)
}
