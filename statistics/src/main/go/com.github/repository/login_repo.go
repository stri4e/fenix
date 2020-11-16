package repository

import (
	"github.com/jinzhu/gorm"
	"statistics/src/main/go/com.github/entity"
)

type LoginRepo struct {
	database *gorm.DB
}

func NewLoginRepo(database *gorm.DB) *LoginRepo {
	return &LoginRepo{database: database}
}

func (repo *LoginRepo) FindByUserId(userId string) ([]*entity.Login, error) {
	var login []*entity.Login
	err := repo.database.
		Where("user_id = ?", userId).
		Find(&login).Error
	return login, err
}

func (repo *LoginRepo) FindBetweenTime(start string, end string) ([]*entity.Login, error) {
	var login []*entity.Login
	err := repo.database.
		Order("create_at").
		Where("create_at BETWEEN ? AND ?", start, end).
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
