package handlers

import (
	"../controllers"
	"../utils"
	"github.com/gorilla/mux"
	"github.com/prometheus/common/log"
	"net/http"
	"strconv"
)

type ManagerHandler struct {
	controller *controllers.ManagersController
}

func NewManagerHandler(controller *controllers.ManagersController) *ManagerHandler {
	return &ManagerHandler{controller: controller}
}

func (handler *ManagerHandler) FindManagerByOrder(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	strOrderId := vars["orderId"]
	if utils.IsBlank(strOrderId) {
		ErrorSender(w, http.StatusBadRequest, "Request path is required.")
		return
	}
	orderId, err := strconv.ParseUint(strOrderId, 10, 64)
	payload, err := handler.controller.FindManagerByOrderId(uint(orderId))
	if err != nil {
		ErrorSender(w, http.StatusNotFound, "Manager not found")
		return
	}
	log.Debug("Enter: find manager information")
	ResponseSender(w, payload, http.StatusOK)
}
