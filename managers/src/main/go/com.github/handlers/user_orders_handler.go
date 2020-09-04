package handlers

import (
	"../controllers"
	"../utils"
	"github.com/gorilla/mux"
	"github.com/prometheus/common/log"
	"net/http"
	"strconv"
)

type UserOrderHandler struct {
	controller *controllers.UserOrdersController
}

func (handler *UserOrderHandler) FindBindingOrders(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	strOrderId := vars["orderId"]
	if utils.IsBlank(strOrderId) {
		ErrorSender(w, http.StatusBadRequest, "Request path is required.")
		return
	}
	orderId, err := strconv.ParseUint(strOrderId, 10, 64)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't deserialize Payload.")
		return
	}
	payload, err := handler.controller.FindBindingOrders(uint(orderId))
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Purchases not found")
		return
	}
	log.Debug("Enter: find orders information")
	ResponseSender(w, payload, http.StatusOK)
}
