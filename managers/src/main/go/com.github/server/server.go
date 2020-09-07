package server

import (
	"../config"
	"../docs"
	"../handlers"
	"../logger"
	"../migration"
	"../services"
	log "github.com/sirupsen/logrus"
	"net/http"
)

type Server struct {
	config            *config.Config
	logger            *logger.Logger
	eurekaService     *services.EurekaService
	handler           *handlers.RestHandler
	dataBaseContainer *migration.DataBaseMigration
}

func NewServer(
	config *config.Config,
	logger *logger.Logger,
	eurekaService *services.EurekaService,
	handler *handlers.RestHandler,
	dataBaseContainer *migration.DataBaseMigration) *Server {
	return &Server{
		config:            config,
		logger:            logger,
		eurekaService:     eurekaService,
		handler:           handler,
		dataBaseContainer: dataBaseContainer,
	}
}

func (server *Server) Run() {
	httpServer := &http.Server{
		Addr:    ":" + server.config.ServerPort,
		Handler: server.handler.Handler(),
	}
	docs.SwaggerInfo.Host = server.config.SwaggerHost
	server.logger.InitLogger()
	err := server.dataBaseContainer.RunBuildDataBase()
	if server.config.EurekaConfig.EnableEureka {
		go server.eurekaService.Run()
	}
	err = httpServer.ListenAndServe()
	if err != nil {
		log.Error("Server crashed!")
	}
}
