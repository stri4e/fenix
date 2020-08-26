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
	ordersHandler *ItemsHandler
	config        *config.Config
}

func NewRestHandler(ordersHandler *ItemsHandler, config *config.Config) *RestHandler {
	return &RestHandler{ordersHandler: ordersHandler, config: config}
}

func (handler *RestHandler) Handler() http.Handler {
	tracer, err := handler.newTracer()
	if err != nil {
		log.Fatal(err)
	}
	serverMiddleware := zipkinhttp.NewServerMiddleware(tracer, zipkinhttp.SpanName("request"))
	router := mux.NewRouter()
	router.
		HandleFunc("/v1", handler.ordersHandler.SaveItem).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/{status}", handler.ordersHandler.FindItemsByStatus).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/all/{status}", handler.ordersHandler.FindItemsAllByStatus).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/{orderId}/{status}", handler.ordersHandler.UpdateStatusItem).
		Methods(http.MethodPut)
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
