package entity

import (
	"github.com/jinzhu/gorm"
)

type Purchase struct {
	gorm.Model
	UserId   uint               `gorm:"column:user_id"`
	OrderId  uint               `gorm:"column:order_id"`
	Customer *Customer          `gorm:"foreignkey:PurchaseId"`
	Products []*PurchaseProduct `gorm:"foreignkey:PurchaseId"`
	Amount   float64            `gorm:"column:amount"`
	Status   string             `gorm:"column:status"`
	Manager  *Manager           `gorm:"foreignkey:PurchaseId"`
}

func (purchase *Purchase) TableName() string {
	return "purchases"
}
