package handlers

import (
	"../controllers"
	log "../logger"
	"encoding/binary"
	"io/ioutil"
	"net/http"
)

type NewOrdersHandler struct {
	controller *controllers.NewOrdersController
}

func NewNewOrdersHandler(controller *controllers.NewOrdersController) *NewOrdersHandler {
	return &NewOrdersHandler{controller: controller}
}

func (handler *NewOrdersHandler) FindBetweenTime(w http.ResponseWriter, r *http.Request) {
	start := r.FormValue("start")
	end := r.FormValue("end")
	if start == "" && end == "" {
		http.Error(w, "Required params is empty!", http.StatusBadRequest)
		return
	}
	login, err := handler.controller.FindBetweenTime(start, end)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: read all orders information between", start, end)
	ResponseSender(w, login, http.StatusOK)
}

func (handler *NewOrdersHandler) CreateOrder(w http.ResponseWriter, r *http.Request) {
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	defer r.Body.Close()
	err = handler.controller.CreateOrder(uint(binary.BigEndian.Uint64(body)))
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new  user order information")
	ResponseSender(w, "", http.StatusCreated)
}
