package handlers

import (
	"../controllers"
	log "../logger"
	"../utils"
	"encoding/binary"
	"github.com/gorilla/mux"
	"io/ioutil"
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
	products, err := handler.controller.FindByUserId(uint(userId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	log.Debug("Enter: read all views information by account id")
	ResponseSender(w, products, http.StatusOK)
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
	body, err := ioutil.ReadAll(r.Body)
	defer r.Body.Close()
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	err = handler.controller.CreateView(userId, uint(binary.BigEndian.Uint64(body)))
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new view by user id")
	ResponseSender(w, "", http.StatusCreated)
}
