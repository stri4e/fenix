package repository

import (
	"../entity"
	"github.com/jinzhu/gorm"
)

type LoginRepo struct {
	database *gorm.DB
}

func NewLoginRepo(database *gorm.DB) *LoginRepo {
	return &LoginRepo{database: database}
}

func (repo *LoginRepo) FindByAccountId(accountId uint) (*entity.Login, error)  {
	var login *entity.Login
	err := repo.database.
		Preload("product").
		Where("WHERE account_id = ?", accountId).
		Find(&login).Error
	return login, err
}

func (repo *LoginRepo) Save(login *entity.Login) (*entity.Login, error) {
	tx := repo.database.Begin()
	err := tx.Save(&login).Error
	if err != nil {
		tx.Rollback()
		return nil, err
	}
	tx.Commit()
	return login, nil
}
