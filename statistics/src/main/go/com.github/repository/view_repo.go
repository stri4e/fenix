package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

const (
	ViewProductColumn       = "Product"
	ViewProductImagesColumn = "Product.Images"
)

type ViewRepo struct {
	database *gorm.DB
}

func NewViewRepo(database *gorm.DB) *ViewRepo {
	return &ViewRepo{database: database}
}

func (repo ViewRepo) FindByUserId(userId string) ([]*entity.View, error) {
	var views []*entity.View
	err := repo.database.
		Preload(ViewProductColumn).
		Preload(ViewProductImagesColumn).
		Order("created_at").
		Where("user_id = ?", userId).
		Find(&views).Error
	return views, err
}

func (repo ViewRepo) FindBetweenTime(start string, end string) ([]*entity.View, error) {
	var views []*entity.View
	err := repo.database.
		Preload(ViewProductColumn).
		Preload(ViewProductImagesColumn).
		Order("created_at").
		Where("create_at BETWEEN ? AND ?", start, end).
		Find(&views).Error
	return views, err
}

func (repo ViewRepo) FindViews(userId string) ([]*entity.View, error) {
	var views []*entity.View
	err := repo.database.
		Preload(ViewProductColumn).
		Preload(ViewProductImagesColumn).
		Order("created_at").
		Limit(20).
		Where("user_id = ?", userId).
		Find(&views).Error
	return views, err
}

func (repo ViewRepo) Save(view *entity.View) error {
	tx := repo.database.Begin()
	err := tx.Save(&view).Error
	if err != nil {
		return err
	}
	tx.Commit()
	return nil
}
