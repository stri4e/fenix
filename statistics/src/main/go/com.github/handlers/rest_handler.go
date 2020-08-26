package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	"github.com/openzipkin/zipkin-go"
	zipkinhttp "github.com/openzipkin/zipkin-go/middleware/http"
	"github.com/openzipkin/zipkin-go/model"
	httpreporter "github.com/openzipkin/zipkin-go/reporter/http"
	httpSwagger "github.com/swaggo/http-swagger"
	"log"
	"net/http"
	"strconv"
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
	tracer, err := handler.newTracer()
	if err != nil {
		log.Fatal(err)
	}
	serverMiddleware := zipkinhttp.NewServerMiddleware(tracer, zipkinhttp.SpanName("request"))
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

	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	router.Use(serverMiddleware)
	http.Handle("/", router)
	return router
}

func (handler *RestHandler) newTracer() (*zipkin.Tracer, error) {
	reporter := httpreporter.NewReporter(handler.config.ZipkinUrl)
	port, _ := strconv.ParseUint(handler.config.ServerPort, 10, 64)
	localEndpoint := &model.Endpoint{ServiceName: handler.config.ApplicationName, Port: uint16(port)}
	sampler, err := zipkin.NewCountingSampler(1)
	if err != nil {
		return nil, err
	}
	t, err := zipkin.NewTracer(
		reporter,
		zipkin.WithSampler(sampler),
		zipkin.WithLocalEndpoint(localEndpoint),
	)
	if err != nil {
		return nil, err
	}
	return t, err
}
