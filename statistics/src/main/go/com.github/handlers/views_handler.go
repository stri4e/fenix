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

type ViewsHandler struct {
	controller *controllers.ViewsController
}

func NewViewsHandler(controller *controllers.ViewsController) *ViewsHandler {
	return &ViewsHandler{controller: controller}
}

func (handler *ViewsHandler) FindByUserId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["userId"]
	userId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	views, err := handler.controller.FindByUserId(uint(userId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all views information by account id")
	ResponseSender(w, views, http.StatusOK)
}

func (handler *ViewsHandler) FindBetweenTime(w http.ResponseWriter, r *http.Request) {
	start := r.FormValue("start")
	end := r.FormValue("end")
	if start == "" && end == "" {
		http.Error(w, "Required params is empty!", http.StatusBadRequest)
		return
	}
	views, err := handler.controller.FindBetweenTime(start, end)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all views information between ", start, end)
	ResponseSender(w, views, http.StatusOK)
}

func (handler *ViewsHandler) FindViews(w http.ResponseWriter, r *http.Request) {
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
	views, err := handler.controller.FindViews(userId)
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all views information by account id")
	ResponseSender(w, views, http.StatusOK)
}

func (handler *ViewsHandler) CreateViews(w http.ResponseWriter, r *http.Request) {
	tokenHeader := r.Header.Get("Authorization")
	if tokenHeader == "" {
		http.Error(w, "", http.StatusForbidden)
		return
	}
	userId, err := utils.GetSubject(tokenHeader)
	var payload dto.ProductDto
	err = json.NewDecoder(r.Body).Decode(&payload)
	view, err := handler.controller.CreateView(userId, &payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new view by account id")
	ResponseSender(w, view, http.StatusCreated)
}
