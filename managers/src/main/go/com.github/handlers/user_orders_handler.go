package handlers

import (
	"../controllers"
	"../utils"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strconv"
)

type UserOrderHandler struct {
	controller *controllers.UserOrdersController
}

func NewUserOrderHandler(controller *controllers.UserOrdersController) *UserOrderHandler {
	return &UserOrderHandler{controller: controller}
}

func (handler *UserOrderHandler) FindBindingOrders(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strOrderId := vars["orderId"]
			utils.ThrowIfNil(strOrderId, http.StatusBadRequest, "Request path is required.")
			orderId, err := strconv.ParseUint(strOrderId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Arguments must be a number.")
			payload, err := handler.controller.FindBindingOrders(uint(orderId))
			utils.ThrowIfErr(err, http.StatusBadRequest, "Purchases not found")
			log.WithFields(log.Fields{"orderId": orderId}).Debug("Enter: orders information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
