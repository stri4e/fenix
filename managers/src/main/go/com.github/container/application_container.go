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
	err = container.Provide(config.ConnectDatabase)
	err = container.Provide(migration.NewDataBaseMigration)
	err = container.Provide(logger.NewLogger)

	err = container.Provide(repository.NewManagersRepository)
	err = container.Provide(repository.NewPurchaseRepository)

	err = container.Provide(services.NewEurekaService)
	err = container.Provide(services.NewManagersService)
	err = container.Provide(services.NewPurchaseService)
	err = container.Provide(services.NewProductService)
	err = container.Provide(services.NewOrderService)

	err = container.Provide(controllers.NewPurchasesController)
	err = container.Provide(controllers.NewUserOrdersController)
	err = container.Provide(controllers.NewManagersController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewPurchasesHandler)
	err = container.Provide(handlers.NewUserOrderHandler)
	err = container.Provide(handlers.NewManagerHandler)
	err = container.Provide(handlers.NewTracer)

	err = container.Provide(server.NewServer)

	if err != nil {
		panic(err)
	}
	return container
}
