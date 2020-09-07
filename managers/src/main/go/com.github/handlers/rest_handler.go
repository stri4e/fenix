package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
	"net/http"
)

type RestHandler struct {
	ordersHandler    *PurchasesHandler
	userOrderHandler *UserOrderHandler
	managerHandler   *ManagerHandler
	config           *config.Config
	tracer           *Tracer
}

func NewRestHandler(
	ordersHandler *PurchasesHandler,
	userOrderHandler *UserOrderHandler,
	managerHandler *ManagerHandler,
	config *config.Config,
	tracer *Tracer) *RestHandler {
	return &RestHandler{
		ordersHandler:    ordersHandler,
		userOrderHandler: userOrderHandler,
		managerHandler:   managerHandler,
		config:           config,
		tracer:           tracer,
	}
}

func (handler *RestHandler) Handler() http.Handler {
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
	router.HandleFunc("/v1/binding/{orderId}", handler.userOrderHandler.FindBindingOrders).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/fetch/{orderId}", handler.managerHandler.FindManagerByOrder).
		Methods(http.MethodGet)
	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Methods(
			http.MethodGet,
			http.MethodPut,
			http.MethodPatch,
			http.MethodOptions,
		).Handler(httpSwagger.WrapHandler)
		router.Use(mux.CORSMethodMiddleware(router))
	}
	if handler.config.ZipkinEnable {
		zipkinMiddleware := handler.tracer.CreateMiddleware()
		router.Use(zipkinMiddleware)
	}
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}
