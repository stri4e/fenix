package handlers

import (
	"../controllers"
	"../dto"
	log "../logger"
	"encoding/json"
	"net/http"
)

type ManagersHandler struct {
	controller *controllers.ItemsController
}

func NewManagersHandler(controller *controllers.ItemsController) *ManagersHandler {
	return &ManagersHandler{controller: controller}
}

func (handler *ManagersHandler) SaveOrUpdateManagerOrders(w http.ResponseWriter, r *http.Request) {
	var payload dto.ItemDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	err = handler.controller.SaveItem(1, "Vasia", "Pupkin", &payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new  manger orders information")
	ResponseSender(w, "", http.StatusCreated)
}
