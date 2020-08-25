package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

const (
	ProductColumn        = "Products"
	CustomerColumn       = "Customer"
	ProductsImagesColumn = "Products.Images"
)

type PurchaseRepo struct {
	database *gorm.DB
}

func NewPurchaseRepo(database *gorm.DB) *PurchaseRepo {
	return &PurchaseRepo{database: database}
}

func (repo *PurchaseRepo) FindByUserId(userId uint) ([]*entity.Purchase, error) {
	var data []*entity.Purchase
	err := repo.database.
		Preload(ProductColumn).
		Preload(CustomerColumn).
		Preload(ProductsImagesColumn).
		Order("created_at").
		Where("user_id = ?", userId).
		Find(&data).Error
	return data, err
}

func (repo *PurchaseRepo) FindBetweenTime(start string, end string) ([]*entity.Purchase, error) {
	var data []*entity.Purchase
	err := repo.database.
		Preload(ProductColumn).
		Preload(CustomerColumn).
		Preload(ProductsImagesColumn).
		Order("created_at").
		Where("created_at BETWEEN ? AND ?", start, end).
		Find(&data).Error
	return data, err
}

func (repo *PurchaseRepo) FindUserId(orderId uint) (uint, error) {
	var userID uint
	err := repo.database.
		Table("purchase").
		Order("created_at").
		Where("order_id = ?", orderId).
		Select("user_id").Row().Scan(&userID)
	return userID, err
}

func (repo *PurchaseRepo) Save(purchase *entity.Purchase) (*entity.Purchase, error) {
	tx := repo.database.Begin()
	err := tx.Save(&purchase).Error
	if err != nil {
		tx.Rollback()
		return nil, err
	}
	tx.Commit()
	return purchase, nil
}

func (repo *PurchaseRepo) UpdatePurchase(orderId uint, status string, manager *entity.Manager) error {
	tx := repo.database.Begin()
	purchase := new(entity.Purchase)
	err := tx.
		Preload(ProductColumn).
		Preload(CustomerColumn).
		Preload(ProductsImagesColumn).
		Preload("Manager").
		Take(&purchase, "order_id = ?", orderId).Error
	if err != nil {
		tx.Rollback()
		return err
	}
	purchase.Manager = manager
	purchase.Status = status
	err = tx.Save(&purchase).Error
	if err != nil {
		tx.Rollback()
		return err
	}
	tx.Commit()
	return nil
}
