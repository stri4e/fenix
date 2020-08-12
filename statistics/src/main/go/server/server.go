package server

import (
	"../config"
	"../handlers"
	"../logger"
	"../migration"
	"../services"
	"net/http"
)

type Server struct {
	config            *config.Config
	logger            *logger.Logger
	handler           *handlers.RestHandler
	eurekaService     *services.EurekaService
	dataBaseContainer *migration.DataBaseMigration
}

func NewServer(config *config.Config, logger *logger.Logger, handler *handlers.RestHandler, eurekaService *services.EurekaService, dataBaseContainer *migration.DataBaseMigration) *Server {
	return &Server{config: config, logger: logger, handler: handler, eurekaService: eurekaService, dataBaseContainer: dataBaseContainer}
}

func (server *Server) Run() {
	httpServer := &http.Server{
		Addr:    ":" + server.config.ServerPort,
		Handler: server.handler.Handler(),
	}
	server.logger.InitLogger()
	err := server.dataBaseContainer.RunBuildDataBase()
	if server.config.EurekaConfig.EnableEureka {
		go server.eurekaService.Run()
	}
	err = httpServer.ListenAndServe()
	if err != nil {
		logger.Error("Server crashed!")
	}
}
