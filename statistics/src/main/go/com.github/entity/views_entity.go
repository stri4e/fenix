package entity

import (
	"github.com/jinzhu/gorm"
)

type View struct {
	gorm.Model
	UserId    uint             `gorm:"column:user_id"`
	Products  []*ViewedProduct `gorm:"foreignkey:PurchaseId"`
}

func (purchase *View) TableName() string {
	return "views"
}
