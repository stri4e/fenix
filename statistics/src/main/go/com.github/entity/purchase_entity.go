package entity

import (
	"time"
)

type Purchase struct {
	Id        uint               `gorm:"column:id"`
	AccountId uint               `gorm:"column:account_id"`
	OrderId   uint               `gorm:"column:order_id"`
	CreatedAt time.Time          `gorm:"column:created_at"`
	Customer  *Customer          `gorm:"foreignkey:PurchaseId"`
	Products  []*PurchaseProduct `gorm:"foreignkey:PurchaseId"`
	Amount    float64            `gorm:"column:amount"`
	Status    string             `gorm:"column:status"`
}

func (purchase *Purchase) TableName() string {
	return "purchase"
}
