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
	err = container.Provide(repository.NewItemRepository)

	err = container.Provide(services.NewEurekaService)
	err = container.Provide(services.NewManagersService)
	err = container.Provide(services.NewItemService)
	err = container.Provide(services.NewProductService)
	err = container.Provide(services.NewOrderService)
	err = container.Provide(services.NewPurchaseService)

	err = container.Provide(controllers.NewItemsController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewItemsHandler)

	err = container.Provide(server.NewServer)

	if err != nil {
		panic(err)
	}
	return container
}
