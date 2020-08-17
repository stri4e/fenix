package entity

import (
	"github.com/jinzhu/gorm"
)

type View struct {
	gorm.Model
	UserId  uint           `gorm:"column:user_id"`
	Product *ViewedProduct `gorm:"foreignkey:ViewId"`
}

func (purchase *View) TableName() string {
	return "views"
}
