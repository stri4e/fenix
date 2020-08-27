package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	"github.com/prometheus/client_golang/prometheus/promhttp"
	httpSwagger "github.com/swaggo/http-swagger"
	"net/http"
)

type RestHandler struct {
	purchaseHandler *PurchasesHandler
	loginHandler    *LoginHandler
	viewsHandler    *ViewsHandler
	config          *config.Config
	tracer          *Tracer
}

func NewRestHandler(
	purchaseHandler *PurchasesHandler,
	loginHandler *LoginHandler,
	viewsHandler *ViewsHandler,
	config *config.Config,
	tracer *Tracer) *RestHandler {
	return &RestHandler{
		purchaseHandler: purchaseHandler,
		loginHandler:    loginHandler,
		viewsHandler:    viewsHandler,
		config:          config,
		tracer:          tracer,
	}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1/purchases/fetch/{userId}", handler.purchaseHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases/fetch", handler.purchaseHandler.FindBetweenTime).
		Queries("start", "{start}", "end", "{end}").
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases", handler.purchaseHandler.FindPurchases).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases/fetch/{orderId}", handler.purchaseHandler.FindPurchasesByOrderId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases/edit", handler.purchaseHandler.CreatePurchase).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/purchases/manager/edit/{orderId}/{status}", handler.purchaseHandler.CreateManagerPurchase).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/purchases/edit/{orderId}/{status}", handler.purchaseHandler.UpdateStatusPurchase).
		Methods(http.MethodPut)
	router.
		HandleFunc("/v1/logins/fetch/{userId}", handler.loginHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/fetch", handler.loginHandler.FindBetweenTime).
		Queries("start", "{start}", "end", "{end}").
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/edit", handler.loginHandler.CreateLogin).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/views/fetch/{userId}", handler.viewsHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views/fetch", handler.viewsHandler.FindBetweenTime).
		Queries("start", "{start}", "end", "{end}").
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.FindViews).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.CreateViews).
		Methods(http.MethodPost)

	router.Handle("/metrics", promhttp.Handler())

	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	zipkinMiddleware := handler.tracer.CreateMiddleware()
	router.Use(zipkinMiddleware)
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}
