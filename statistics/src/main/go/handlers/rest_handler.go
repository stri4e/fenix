package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
	"log"
	"net/http"
)

type RestHandler struct {
	accountHandler  *AccountsHandler
	purchaseHandler *PurchasesHandler
	loginHandler    *LoginHandler
	viewsHandler    *ViewsHandler
	config          *config.Config
}

func NewRestHandler(
	accountHandler *AccountsHandler, purchaseHandler *PurchasesHandler,
	loginHandler *LoginHandler, viewsHandler *ViewsHandler, config *config.Config) *RestHandler {
	return &RestHandler{
		accountHandler:  accountHandler,
		purchaseHandler: purchaseHandler,
		loginHandler:    loginHandler,
		viewsHandler:    viewsHandler,
		config:          config}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1/accounts/{userId}", handler.accountHandler.GetByUserId).
		Methods("GET")
	router.
		HandleFunc("/v1/accounts", handler.accountHandler.CreateAccount).
		Methods("POST")
	router.
		HandleFunc("/v1/purchases/{accountId}", handler.purchaseHandler.GetByAccountId).
		Methods("GET")
	router.
		HandleFunc("/v1/purchases", handler.purchaseHandler.CreatePurchase).
		Methods("POST")
	router.
		HandleFunc("/v1/logins/{accountId}", handler.loginHandler.GetByAccountId).
		Methods("GET")
	router.
		HandleFunc("/v1/logins", handler.loginHandler.CreateLogin).
		Methods("POST")
	router.
		HandleFunc("/v1/views/{accountId}", handler.viewsHandler.GetByAccountId).
		Methods("GET")
	router.
		HandleFunc("/v1/views", handler.viewsHandler.CreateViews).
		Methods("POST")
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
