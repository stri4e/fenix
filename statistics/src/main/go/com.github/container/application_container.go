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
	err = container.Provide(config.ConnectEureka)
	err = container.Provide(logger.NewLogger)
	err = container.Provide(config.ConnectDatabase)
	err = container.Provide(migration.NewDataBaseMigration)

	err = container.Provide(repository.NewViewRepo)
	err = container.Provide(repository.NewLoginRepo)
	err = container.Provide(repository.NewNewOrdersRepo)

	err = container.Provide(services.NewLoginService)
	err = container.Provide(services.NewViewService)
	err = container.Provide(services.NewEurekaService)
	err = container.Provide(services.NewProductService)
	err = container.Provide(services.NewOrderService)
	err = container.Provide(services.NewNewOrdersService)

	err = container.Provide(controllers.NewLoginsController)
	err = container.Provide(controllers.NewViewsController)
	err = container.Provide(controllers.NewNewOrdersController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewLoginHandler)
	err = container.Provide(handlers.NewViewsHandler)
	err = container.Provide(handlers.NewNewOrdersHandler)
	err = container.Provide(handlers.NewTracer)

	err = container.Provide(server.NewServer)

	if err != nil {
		panic(err)
	}
	return container
}
