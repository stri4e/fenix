package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
	"log"
	"net/http"
)

type RestHandler struct {
	purchaseHandler *PurchasesHandler
	loginHandler    *LoginHandler
	viewsHandler    *ViewsHandler
	config          *config.Config
}

func NewRestHandler(
	purchaseHandler *PurchasesHandler, loginHandler *LoginHandler,
	viewsHandler *ViewsHandler, config *config.Config) *RestHandler {
	return &RestHandler{
		purchaseHandler: purchaseHandler,
		loginHandler:    loginHandler,
		viewsHandler:    viewsHandler,
		config:          config}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1/purchases/fetch/{userId}", handler.purchaseHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases", handler.purchaseHandler.FindPurchases).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases/{orderId}", handler.purchaseHandler.FindPurchasesByOrderId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases/edit", handler.purchaseHandler.CreatePurchase).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/logins/fetch/{userId}", handler.loginHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/edit", handler.loginHandler.CreateLogin).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/views/fetch/{userId}", handler.viewsHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.FindViews).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.CreateViews).
		Methods(http.MethodPost)
	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	router.Use(loggingMiddleware)
	http.Handle("/", router)
	return router
}

func loggingMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		log.Println(
			http.TimeFormat,
			"Method:", r.Method,
			"URL:", r.RequestURI)
		next.ServeHTTP(w, r)
	})
}
