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
	err = container.Provide(logger.NewLogger)
	err = container.Provide(config.ConnectDatabase)
	err = container.Provide(migration.NewDataBaseMigration)

	err = container.Provide(repository.NewAccountRepo)
	err = container.Provide(repository.NewPurchaseRepo)
	err = container.Provide(repository.NewViewRepo)
	err = container.Provide(repository.NewLoginRepo)

	err = container.Provide(services.NewAccountService)
	err = container.Provide(services.NewPurchaseService)
	err = container.Provide(services.NewLoginService)
	err = container.Provide(services.NewViewService)
	err = container.Provide(services.NewEurekaService)

	err = container.Provide(controllers.NewAccountController)
	err = container.Provide(controllers.NewPurchasesController)
	err = container.Provide(controllers.NewLoginsController)
	err = container.Provide(controllers.NewViewsController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewAccountsHandler)
	err = container.Provide(handlers.NewPurchasesHandler)
	err = container.Provide(handlers.NewLoginHandler)
	err = container.Provide(handlers.NewViewsHandler)

	err = container.Provide(server.NewServer)

	if err != nil {
		panic(err)
	}
	return container
}
