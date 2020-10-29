package entity

import (
	"github.com/jinzhu/gorm"
)

type View struct {
	gorm.Model
	UserId    string `gorm:"column:user_id"`
	ProductId uint   `gorm:"column:product_id"`
}

func (purchase *View) TableName() string {
	return "views"
}
