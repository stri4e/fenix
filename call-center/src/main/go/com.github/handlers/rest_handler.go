package handlers

import "net/http"

type RestHandler struct {
	ordersHandler *ManagersHandler
}

func NewRestHandler(ordersHandler *ManagersHandler) *RestHandler {
	return &RestHandler{ordersHandler: ordersHandler}
}

func (handler *RestHandler) Handler() http.Handler {
	return nil
}
