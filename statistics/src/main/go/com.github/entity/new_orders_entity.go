package entity

import "github.com/jinzhu/gorm"

type NewOrder struct {
	gorm.Model
	OrderId uint `gorm:"column:order_id"`
}

func (purchase *NewOrder) TableName() string {
	return "new_orders"
}
