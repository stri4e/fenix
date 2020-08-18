package repository

import "github.com/jinzhu/gorm"

type ManagersRepository struct {
	database *gorm.DB
}

func NewManagersRepository(database *gorm.DB) *ManagersRepository {
	return &ManagersRepository{database: database}
}
