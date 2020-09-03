package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
	"net/http"
)

type RestHandler struct {
	ordersHandler *PurchasesHandler
	config        *config.Config
	tracer          *Tracer
}

func NewRestHandler(ordersHandler *PurchasesHandler, config *config.Config, tracer *Tracer) *RestHandler {
	return &RestHandler{ordersHandler: ordersHandler, config: config, tracer: tracer}
}

func (handler *RestHandler) Handler() http.Handler {
	zipkinMiddleware := handler.tracer.CreateMiddleware()
	router := mux.NewRouter()
	router.
		HandleFunc("/v1", handler.ordersHandler.SavePurchase).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/{status}", handler.ordersHandler.FindPurchasesByStatus).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/all/{status}", handler.ordersHandler.FindPurchasesAllByStatus).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/{orderId}/{status}", handler.ordersHandler.UpdateStatusPurchase).
		Methods(http.MethodPut)
	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	router.Use(zipkinMiddleware)
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}

