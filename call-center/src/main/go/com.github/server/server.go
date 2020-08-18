package server

import (
	"../config"
	"../handlers"
	"../logger"
	"../services"
	"net/http"
)

type Server struct {
	config        *config.Config
	logger        *logger.Logger
	eurekaService *services.EurekaService
	handler       *handlers.RestHandler
}

func NewServer(config *config.Config, logger *logger.Logger, eurekaService *services.EurekaService, handler *handlers.RestHandler) *Server {
	return &Server{config: config, logger: logger, eurekaService: eurekaService, handler: handler}
}

func (server *Server) Run() {
	httpServer := &http.Server{
		Addr:    ":" + server.config.ServerPort,
		Handler: server.handler.Handler(),
	}
	if server.config.EurekaConfig.EnableEureka {
		go server.eurekaService.Run()
	}
	err := httpServer.ListenAndServe()
	if err != nil {
		logger.Error("Server crashed!")
	}
}
