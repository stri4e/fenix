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

func (handler *PurchasesHandler) FindByUserId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["userId"]
	accountId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	purchase, err := handler.controller.FindByUserId(uint(accountId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all purchase information by account id")
	ResponseSender(w, purchase, http.StatusOK)
}

func (handler *PurchasesHandler) FindBetweenTime(w http.ResponseWriter, r *http.Request) {
	start := r.FormValue("start")
	end := r.FormValue("end")
	if start == "" && end == "" {
		http.Error(w, "Required params is empty!", http.StatusBadRequest)
		return
	}
	purchase, err := handler.controller.FindBetweenTime(start, end)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all purchase information between", start, end)
	ResponseSender(w, purchase, http.StatusOK)
}

func (handler *PurchasesHandler) FindPurchases(w http.ResponseWriter, r *http.Request) {
	tokenHeader := r.Header.Get("Authorization")
	if tokenHeader == "" {
		http.Error(w, "", http.StatusForbidden)
		return
	}
	userId, err := utils.GetSubject(tokenHeader)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	purchase, err := handler.controller.FindPurchases(userId)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all purchase information by account id")
	ResponseSender(w, purchase, http.StatusOK)
}

func (handler *PurchasesHandler) FindPurchasesByOrderId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["orderId"]
	orderId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	purchase, err := handler.controller.FindPurchasesByOrderId(uint(orderId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all purchase information by account id")
	ResponseSender(w, purchase, http.StatusOK)
}

func (handler *PurchasesHandler) CreatePurchase(w http.ResponseWriter, r *http.Request) {
	var payload dto.PurchaseDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	purchase, err := handler.controller.CreatePurchase(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new purchase by account id success")
	ResponseSender(w, purchase, http.StatusCreated)
}

func (handler *PurchasesHandler) CreateManagerPurchase(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	orderIdStr := vars["orderId"]
	status := vars["status"]
	if utils.IsBlank(orderIdStr) || utils.IsBlank(status) {
		http.Error(w, "", http.StatusBadRequest)
		return
	}
	orderId, err := strconv.ParseUint(orderIdStr, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	var payload dto.ManagerDto
	err = json.NewDecoder(r.Body).Decode(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	err = handler.controller.CreateManagerPurchase(uint(orderId), status, &payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: update orders information")
	ResponseSender(w, "", http.StatusOK)
}

func (handler *PurchasesHandler) UpdateStatusPurchase(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	orderIdStr := vars["orderId"]
	status := vars["status"]
	if utils.IsBlank(orderIdStr) || utils.IsBlank(status) {
		http.Error(w, "", http.StatusBadRequest)
		return
	}
	orderId, err := strconv.ParseUint(orderIdStr, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	err = handler.controller.UpdateStatusPurchase(uint(orderId), status)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: update orders information")
	ResponseSender(w, "", http.StatusOK)
}
