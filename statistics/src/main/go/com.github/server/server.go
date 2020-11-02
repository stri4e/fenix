package server

import (
	log "github.com/sirupsen/logrus"
	"net/http"
	"statistics/src/main/go/com.github/config"
	"statistics/src/main/go/com.github/docs"
	"statistics/src/main/go/com.github/handlers"
	"statistics/src/main/go/com.github/logger"
	"statistics/src/main/go/com.github/migration"
	"statistics/src/main/go/com.github/services"
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
