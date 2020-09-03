package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

type NewOrdersRepo struct {
	database *gorm.DB
}

func NewNewOrdersRepo(database *gorm.DB) *NewOrdersRepo {
	return &NewOrdersRepo{database: database}
}

func (repo NewOrdersRepo) FindBetweenTime(start string, end string) ([]*entity.NewOrder, error) {
	var views []*entity.NewOrder
	err := repo.database.
		Order("created_at").
		Where("create_at BETWEEN ? AND ?", start, end).
		Find(&views).Error
	return views, err
}

func (repo NewOrdersRepo) Save(order *entity.NewOrder) error {
	tx := repo.database.Begin()
	err := tx.Save(&order).Error
	if err != nil {
		return err
	}
	tx.Commit()
	return nil
}
