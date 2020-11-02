package handlers

import (
	"github.com/openzipkin/zipkin-go"
	zipkinhttp "github.com/openzipkin/zipkin-go/middleware/http"
	"github.com/openzipkin/zipkin-go/model"
	httpreporter "github.com/openzipkin/zipkin-go/reporter/http"
	"log"
	"net/http"
	"statistics/src/main/go/com.github/config"
	"strconv"
)

type Tracer struct {
	config *config.Config
}

func NewTracer(config *config.Config) *Tracer {
	return &Tracer{config: config}
}

func (tracer *Tracer) CreateMiddleware() func(http.Handler) http.Handler {
	t, err := tracer.newTracer()
	if err != nil {
		log.Fatal(err)
	}
	return zipkinhttp.NewServerMiddleware(t, zipkinhttp.SpanName("request"))
}

func (tracer *Tracer) newTracer() (*zipkin.Tracer, error) {
	reporter := httpreporter.NewReporter(tracer.config.ZipkinUrl)
	port, _ := strconv.ParseUint(tracer.config.ServerPort, 10, 64)
	localEndpoint := &model.Endpoint{ServiceName: tracer.config.ApplicationName, Port: uint16(port)}
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
