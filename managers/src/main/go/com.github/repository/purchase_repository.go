package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

type PurchaseRepository struct {
	db *gorm.DB
}

func NewPurchaseRepository(db *gorm.DB) *PurchaseRepository {
	return &PurchaseRepository{db: db}
}

func (repo *PurchaseRepository) SavePurchase(purchase *entity.Purchase) (*entity.Purchase, error) {
	tx := repo.db.Begin()
	err := tx.Save(&purchase).Error
	if err != nil {
		tx.Begin()
	}
	tx.Commit()
	return purchase, err
}

func (repo *PurchaseRepository) FindByOrderId(orderId uint) (*entity.Purchase, error) {
	var purchase *entity.Purchase
	err := repo.db.
		Where("order_id = ?", orderId).
		Find(&purchase).Error
	return purchase, err
}

func (repo *PurchaseRepository) UpdateStatus(orderId uint, status string) error {
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
