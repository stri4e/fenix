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

type PurchasesHandler struct {
	controller *controllers.PurchasesController
}

func NewPurchasesHandler(controller *controllers.PurchasesController) *PurchasesHandler {
	return &PurchasesHandler{controller: controller}
}

func (handler *PurchasesHandler) SavePurchase(w http.ResponseWriter, r *http.Request) {
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
	var payload dto.PurchaseDto
	err = json.NewDecoder(r.Body).Decode(&payload)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't deserialize Payload.")
		return
	}
	err = handler.controller.SavePurchases(uint(managerId), token.FirstName, token.LastName, &payload)
	if err != nil {
		ErrorSender(w, http.StatusBadRequest, "Can't save items.")
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
	payload, err := handler.controller.FindItems(managerId, status)
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
