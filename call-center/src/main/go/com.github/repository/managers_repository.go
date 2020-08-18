package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

type ManagersRepository struct {
	db *gorm.DB
}

func NewManagersRepository(db *gorm.DB) *ManagersRepository {
	return &ManagersRepository{db: db}
}

func (repo *ManagersRepository) FirstOrCreateManager(manager *entity.Manager) (*entity.Manager, error) {
	tx := repo.db.Begin()
	err := tx.FirstOrCreate(&manager).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return manager, err
}
