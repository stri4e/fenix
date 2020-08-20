package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

type ItemRepository struct {
	db *gorm.DB
}

func NewItemRepository(db *gorm.DB) *ItemRepository {
	return &ItemRepository{db: db}
}

func (repo *ItemRepository) SaveItem(item *entity.Item) (*entity.Item, error) {
	tx := repo.db.Begin()
	err := tx.Save(&item).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return item, err
}

func (repo *ItemRepository) UpdateStatus(orderId uint, status string) error {
	tx := repo.db.Begin()
	err := tx.Table("items").
		Where("order_id = ?", orderId).
		Update("status", status).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return err
}
