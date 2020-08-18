package handlers

import (
	"github.com/gorilla/mux"
	"net/http"
)

type RestHandler struct {
	ordersHandler *ManagersHandler
}

func NewRestHandler(ordersHandler *ManagersHandler) *RestHandler {
	return &RestHandler{ordersHandler: ordersHandler}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1", handler.ordersHandler.SaveOrUpdateManagerOrders).
		Methods(http.MethodPost)
	http.Handle("/", router)
	return router
}
