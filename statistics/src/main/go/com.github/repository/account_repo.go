package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

const (
	PurchasesColumn             = "Purchases"
	PurchaseProductsColumn      = "Purchases.Products"
	PurchaseProductImagesColumn = "Purchases.Products.Images"
	PurchaseCustomerColumn      = "Purchases.Customer"
	LoginsColumn                = "Logins"
	ViewsColumn                 = "Views"
	ViewsProductColumn          = "Views.Products"
	ViewsProductImagesColumn    = "Views.Products.Images"
)

type AccountRepo struct {
	database *gorm.DB
}

func NewAccountRepo(database *gorm.DB) *AccountRepo {
	return &AccountRepo{database: database}
}

func (repo *AccountRepo) FindByUserId(userId uint) (*entity.Account, error) {
	var account entity.Account
	err := repo.database.
		Preload(PurchasesColumn).
		Preload(PurchaseProductsColumn).
		Preload(PurchaseCustomerColumn).
		Preload(PurchaseProductImagesColumn).
		Preload(LoginsColumn).
		Preload(ViewsColumn).
		Preload(ViewsProductColumn).
		Preload(ViewsProductImagesColumn).
		Find(&account, "user_id = ?", userId).Error
	return &account, err
}

func (repo *AccountRepo) SaveAccount(account *entity.Account) (*entity.Account, error) {
	tx := repo.database.Begin()
	err := tx.Create(&account).Error
	if err != nil {
		tx.Rollback()
		return nil, err
	}
	tx.Commit()
	return account, nil
}
