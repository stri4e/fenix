package entity

import (
	"time"
)

type View struct {
	Id        uint             `gorm:"column:id"`
	CreatedAt time.Time        `gorm:"column:created_at"`
	UserId    uint             `gorm:"column:user_id"`
	Products  []*ViewedProduct `gorm:"foreignkey:PurchaseId"`
}

func (purchase *View) TableName() string {
	return "views"
}
