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
	utils.Block{
		Try: func() {
			tokenHeader := r.Header.Get("Authorization")
			token := utils.GetToken(tokenHeader)
			utils.ThrowIfNil(token, http.StatusBadRequest, "Can't fetch a subject.")
			managerId, err := strconv.ParseUint(token.Subject, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't fetch a subject.")
			var payload dto.PurchaseDto
			err = json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize payload")
			err = handler.controller.SavePurchases(uint(managerId), token.FirstName, token.LastName, &payload)
			log.WithFields(log.Fields{"OrderId": payload.OrderId, "Status": payload.Status}).
				Debug("Enter: received new purchase.")
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save payload.")
			ResponseSender(w, "", http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *PurchasesHandler) FindPurchasesByStatus(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			status := vars["status"]
			utils.ThrowIfNil(status, http.StatusBadRequest, "Request path is required.")
			tokenHeader := r.Header.Get("Authorization")
			utils.ThrowIfNil(tokenHeader, http.StatusBadRequest, "Can't fetch a subject.")
			managerId, err := utils.GetSubject(tokenHeader)
			payload, err := handler.controller.FindPurchases(managerId, status)
			utils.ThrowIfErr(err, http.StatusNotFound, "Purchases not found!")
			log.Debug("Enter: find orders information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *PurchasesHandler) FindPurchasesAllByStatus(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			status := vars["status"]
			utils.ThrowIfNil(status, http.StatusBadRequest, "Request path is required.")
			payload, err := handler.controller.FindAllPurchases(status)
			utils.ThrowIfErr(err, http.StatusNotFound, "Purchases not found")
			log.Debug("Enter: find orders information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *PurchasesHandler) UpdateStatusPurchase(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strOrderId := vars["orderId"]
			status := vars["status"]
			utils.ThrowIfNil(strOrderId, http.StatusBadRequest, "Request path is required.")
			utils.ThrowIfNil(status, http.StatusBadRequest, "Request path is required.")
			orderId, err := strconv.ParseUint(strOrderId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Arguments must be a number.")
			err = handler.controller.UpdateStatusPurchase(uint(orderId), status)
			utils.ThrowIfErr(err, http.StatusNotFound, "Can't update order.")
			log.Debug("Enter: update orders information")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
