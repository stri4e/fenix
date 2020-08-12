package migration

import (
	"../config"
	"../entity"
	"github.com/jinzhu/gorm"
)

type DataBaseMigration struct {
	config     *config.Config
	connection *gorm.DB
}

func NewDataBaseMigration(config *config.Config, connection *gorm.DB) *DataBaseMigration {
	return &DataBaseMigration{config: config, connection: connection}
}

func (container *DataBaseMigration) RunBuildDataBase() error {
	switch container.config.DatabaseConfig.DdlAuto {
	case "none":
	case "create":
		return createTables(container.connection)
	case "update":
		return updateTables(container.connection)
	}
	return nil
}

func createTables(connection *gorm.DB) error {
	err := connection.DropTableIfExists(
		&entity.PurchaseImage{},
		&entity.PurchaseProduct{},
		&entity.Purchase{},
		&entity.Login{},
		&entity.ViewedProduct{},
		&entity.ViewedImage{},
		&entity.View{},
		&entity.Account{},
		&entity.Customer{},
	).Error
	if err == nil {
		err = connection.CreateTable(
			&entity.PurchaseImage{},
			&entity.PurchaseProduct{},
			&entity.Purchase{},
			&entity.Login{},
			&entity.ViewedProduct{},
			&entity.ViewedImage{},
			&entity.View{},
			&entity.Account{},
			&entity.Customer{},
		).Error
	}
	return err
}

func updateTables(connection *gorm.DB) error {
	err := connection.AutoMigrate(
		&entity.PurchaseImage{},
		&entity.PurchaseProduct{},
		&entity.Purchase{},
		&entity.Login{},
		&entity.ViewedProduct{},
		&entity.ViewedImage{},
		&entity.View{},
		&entity.Account{},
		&entity.Customer{},
	).Error
	return err
}
