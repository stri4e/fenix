package entity

import (
	"time"
)

type Customer struct {
	Id              uint      `gorm:"column:id"`
	PurchaseId      uint      `gorm:"column:purchase_id"`
	CreatedAt       time.Time `gorm:"column:created_at"`
	CustomerName    string    `gorm:"column:customer_name"`
	CustomerAddress string    `gorm:"column:customer_address"`
	CustomerEmail   string    `gorm:"column:customer_email"`
	CustomerPhone   string    `gorm:"column:customer_phone"`
}

func (customer *Customer) TableName() string {
	return "customer"
}
