package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

const (
	ViewProductColumn       = "Products"
	ViewProductImagesColumn = "Products.Images"
)

type ViewRepo struct {
	database *gorm.DB
}

func NewViewRepo(database *gorm.DB) *ViewRepo {
	return &ViewRepo{database: database}
}

func (repo ViewRepo) FindByAccountId(accountId uint) ([]*entity.View, error) {
	var views []*entity.View
	err := repo.database.
		Preload(ViewProductColumn).
		Preload(ViewProductImagesColumn).
		Where("account_id = ?", accountId).
		Find(&views).Error
	return views, err
}

func (repo ViewRepo) Save(view *entity.View) (*entity.View, error) {
	tx := repo.database.Begin()
	err := tx.Save(&view).Error
	if err != nil {
		return nil, err
	}
	tx.Commit()
	return view, nil
}
