package handlers

import (
	"../controllers"
	"../dto"
	"../utils"
	"encoding/json"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strconv"
)

type PurchasesHandler struct {
	controller *controllers.PurchasesController
}

func NewPurchasesHandler(controller *controllers.PurchasesController) *PurchasesHandler {
	return &PurchasesHandler{controller: controller}
}

func (handler *PurchasesHandler) SavePurchase(w http.ResponseWriter, r *http.Request) {
	tokenHeader := r.Header.Get("Authorization")
	token := utils.GetToken(tokenHeader)
	if token == nil {
		log.Debug("Can't fetch authorize token")
		ErrorSender(w, http.StatusBadRequest, "Can't fetch authorize token")
		return
	}
	managerId, err := strconv.ParseUint(token.Subject, 10, 64)
	if err != nil {
		log.Debug("Params mast be number.")
		ErrorSender(w, http.StatusBadRequest, "Params mast be number.")
		return
	}
	var payload dto.PurchaseDto
	err = json.NewDecoder(r.Body).Decode(&payload)
	if err != nil {
		log.Debug("Can't deserialize payload.")
		ErrorSender(w, http.StatusBadRequest, "Can't deserialize payload.")
		return
	}
	err = handler.controller.SavePurchases(uint(managerId), token.FirstName, token.LastName, &payload)
	if err != nil {
		log.WithFields(log.Fields{"orderId": payload.OrderId, "status": payload.Status}).
			Debug("Can't save purchase.")
		ErrorSender(w, http.StatusBadRequest, "Can't save purchase.")
		return
	}
	log.Debug("Enter: create new  manger orders information")
	ResponseSender(w, "", http.StatusCreated)
}

func (handler *PurchasesHandler) FindPurchasesByStatus(w http.ResponseWriter, r *http.Request) {
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
	payload, err := handler.controller.FindPurchases(managerId, status)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Purchases not found")
		return
	}
	log.Debug("Enter: find orders information")
	ResponseSender(w, payload, http.StatusOK)
}

func (handler *PurchasesHandler) FindPurchasesAllByStatus(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	status := vars["status"]
	if utils.IsBlank(status) {
		ErrorSender(w, http.StatusBadRequest, "Request path is required.")
		return
	}
	payload, err := handler.controller.FindAllPurchases(status)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Purchases not found")
		return
	}
	log.Debug("Enter: find orders information")
	ResponseSender(w, payload, http.StatusOK)
}

func (handler *PurchasesHandler) UpdateStatusPurchase(w http.ResponseWriter, r *http.Request) {
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
	err = handler.controller.UpdateStatusPurchase(uint(orderId), status)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't update order.")
		return
	}
	log.Debug("Enter: update orders information")
	ResponseSender(w, "", http.StatusOK)
}
