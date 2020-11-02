package container

import (
	"go.uber.org/dig"
	"managers/src/main/go/com.github/config"
	"managers/src/main/go/com.github/controllers"
	"managers/src/main/go/com.github/handlers"
	"managers/src/main/go/com.github/logger"
	"managers/src/main/go/com.github/migration"
	"managers/src/main/go/com.github/repository"
	"managers/src/main/go/com.github/server"
	"managers/src/main/go/com.github/services"
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
	err = container.Provide(services.NewOrderService)
	err = container.Provide(services.NewCategoryService)
	err = container.Provide(services.NewProductService)
	err = container.Provide(services.NewSpecificationService)
	err = container.Provide(services.NewSubcategoryService)
	err = container.Provide(services.NewFilterService)
	err = container.Provide(services.NewCriteriaService)

	err = container.Provide(controllers.NewPurchasesController)
	err = container.Provide(controllers.NewUserOrdersController)
	err = container.Provide(controllers.NewManagersController)
	err = container.Provide(controllers.NewCategoryController)
	err = container.Provide(controllers.NewProductController)
	err = container.Provide(controllers.NewSpecificationController)
	err = container.Provide(controllers.NewSubcategoryController)
	err = container.Provide(controllers.NewFilterController)
	err = container.Provide(controllers.NewCriteriaController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewPurchasesHandler)
	err = container.Provide(handlers.NewUserOrderHandler)
	err = container.Provide(handlers.NewManagerHandler)
	err = container.Provide(handlers.NewCategoryHandler)
	err = container.Provide(handlers.NewProductHandler)
	err = container.Provide(handlers.NewSpecificationHandler)
	err = container.Provide(handlers.NewSubcategoryHandler)
	err = container.Provide(handlers.NewFilterHandler)
	err = container.Provide(handlers.NewCriteriaHandler)
	err = container.Provide(handlers.NewTracer)

	err = container.Provide(server.NewServer)

	if err != nil {
		panic(err)
	}
	return container
}
