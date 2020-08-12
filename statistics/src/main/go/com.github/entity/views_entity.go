package entity

import (
	"time"
)

type View struct {
	Id        uint             `gorm:"column:id"`
	CreatedAt time.Time        `gorm:"column:created_at"`
	AccountId int64            `gorm:"column:account_id"`
	Products  []*ViewedProduct `gorm:"foreignkey:PurchaseId"`
}

func (purchase *View) TableName() string {
	return "views"
}
