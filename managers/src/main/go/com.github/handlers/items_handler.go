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

type ItemsHandler struct {
	controller *controllers.ItemsController
}

func NewItemsHandler(controller *controllers.ItemsController) *ItemsHandler {
	return &ItemsHandler{controller: controller}
}

func (handler *ItemsHandler) SaveItem(w http.ResponseWriter, r *http.Request) {
	var payload dto.ItemDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	//todo add parsing token
	err = handler.controller.SaveItem(2, "Kolia", "Zubkin", &payload)
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
	payload, err := handler.controller.FindItems(1, status)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: find orders information")
	ResponseSender(w, payload, http.StatusOK)
}

func (handler *ItemsHandler) UpdateStatus(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["orderId"]
	status := vars["status"]
	orderId, err := strconv.ParseUint(result, 10, 64)
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
