package handlers

import (
	"../config"
	"github.com/gorilla/handlers"
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
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	if handler.config.ZipkinEnable {
		zipkinMiddleware := handler.tracer.CreateMiddleware()
		router.Use(zipkinMiddleware)
	}
	cors := handlers.CORS(
		handlers.AllowedHeaders([]string{"content-type"}),
		handlers.AllowedOrigins([]string{"*"}),
		handlers.AllowCredentials(),
	)
	router.Use(cors)
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}
