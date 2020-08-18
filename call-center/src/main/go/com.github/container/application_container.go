package container

import (
	"../config"
	"../controllers"
	"../handlers"
	"../logger"
	"../migration"
	"../repository"
	"../server"
	"../services"
	"go.uber.org/dig"
)

func BuildContainer() *dig.Container {
	container := dig.New()
	err := container.Provide(config.NewConfig)
	err = container.Provide(config.ConnectDatabase)
	err = container.Provide(logger.NewLogger)
	err = container.Provide(server.NewServer)

	err = container.Provide(migration.NewDataBaseMigration)

	err = container.Provide(repository.NewManagersRepository)

	err = container.Provide(services.NewEurekaService)
	err = container.Provide(services.NewManagersService)

	err = container.Provide(controllers.NewManagersController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewManagersHandler)

	if err != nil {
		panic(err)
	}
	return container
}
