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
