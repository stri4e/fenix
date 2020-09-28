package handlers

import (
	"../controllers"
	"../utils"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
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
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strOrderId := vars["orderId"]
			utils.ThrowIfNil(strOrderId, http.StatusBadRequest, "Request path is required.")
			orderId, err := strconv.ParseUint(strOrderId, BaseUint, BitSize)
			payload, err := handler.controller.FindManagerByOrderId(uint(orderId))
			utils.ThrowIfErr(err, http.StatusNotFound, "Manager not found")
			log.Debug("Enter: find manager information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
