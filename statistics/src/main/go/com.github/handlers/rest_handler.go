package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	"github.com/prometheus/client_golang/prometheus/promhttp"
	httpSwagger "github.com/swaggo/http-swagger"
	"net/http"
)

type RestHandler struct {
	loginHandler *LoginHandler
	viewsHandler *ViewsHandler
	config       *config.Config
	tracer       *Tracer
}

func NewRestHandler(
	loginHandler *LoginHandler,
	viewsHandler *ViewsHandler,
	config *config.Config,
	tracer *Tracer) *RestHandler {
	return &RestHandler{
		loginHandler: loginHandler,
		viewsHandler: viewsHandler,
		config:       config,
		tracer:       tracer,
	}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1/logins/fetch/{userId}", handler.loginHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/fetch/time", handler.loginHandler.FindBetweenTime).
		Queries("start", "{start}", "end", "{end}").
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/edit", handler.loginHandler.CreateLogin).
		Methods(http.MethodPost)

	router.
		HandleFunc("/v1/views/fetch/{userId}", handler.viewsHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views/fetch/time", handler.viewsHandler.FindBetweenTime).
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
		mux.CORSMethodMiddleware(router)
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	if handler.config.ZipkinEnable {
		zipkinMiddleware := handler.tracer.CreateMiddleware()
		router.Use(zipkinMiddleware)
	}
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}
