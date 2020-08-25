package entity

import "github.com/jinzhu/gorm"

type Manager struct {
	gorm.Model
	PurchaseId uint   `gorm:"column:purchase_id"`
	ManagerId  uint   `gorm:"column:manager_id"`
	FirstName  string `gorm:"column:first_name"`
	LastName   string `gorm:"column:last_name"`
}
