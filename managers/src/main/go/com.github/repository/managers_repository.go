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
	err := tx.Where("manager_id = ?", manager.ManagerId).
		FirstOrCreate(&manager).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return manager, err
}

func (repo *ManagersRepository) Find(managerId uint, status string) (*entity.Manager, error) {
	var data *entity.Manager
	err := repo.db.Preload("Purchases").
		Order("create_at").
		Where("manager_id = ? AND status = ?", managerId, status).
		Find(&data).Error
	return data, err
}
