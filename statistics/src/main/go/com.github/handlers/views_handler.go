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

type ViewsHandler struct {
	controller *controllers.ViewsController
}

func NewViewsHandler(controller *controllers.ViewsController) *ViewsHandler {
	return &ViewsHandler{controller: controller}
}

func (handler *ViewsHandler) GetByAccountId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["accountId"]
	accountId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	views, err := handler.controller.GetByAccountId(uint(accountId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all views information by account id")
	ResponseSender(w, views, http.StatusOK)
}

func (handler *ViewsHandler) CreateViews(w http.ResponseWriter, r *http.Request) {
	var payload dto.ViewDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	view, err := handler.controller.CreateView(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new view by account id")
	ResponseSender(w, view, http.StatusCreated)
}
