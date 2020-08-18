package config

import (
	"fmt"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/postgres"
	"log"
	"os"
	"time"
)

func ConnectDatabase(config *Config) (*gorm.DB, error) {
	url := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable",
		config.DatabaseConfig.DatabaseHost,
		config.DatabaseConfig.DatabasePort,
		config.DatabaseConfig.DatabaseUser,
		config.DatabaseConfig.DatabasePassword,
		config.DatabaseConfig.DatabaseName,
	)
	connection, err := gorm.Open("postgres", url)
	if err != nil {
		panic(err.Error())
	}
	database := connection.DB()
	db := connection.LogMode(true)
	db.SetLogger(log.New(os.Stdout, "\r\n", 0))
	database.SetMaxIdleConns(config.DatabaseConfig.MaxIdleConns)
	database.SetMaxOpenConns(config.DatabaseConfig.MaxOpenConns)
	database.SetConnMaxLifetime(time.Hour)
	return connection, err
}
