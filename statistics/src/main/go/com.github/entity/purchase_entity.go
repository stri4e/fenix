package entity

import (
	"github.com/jinzhu/gorm"
	"time"
)

type Purchase struct {
	gorm.Model
	UserId    uint               `gorm:"column:user_id"`
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
