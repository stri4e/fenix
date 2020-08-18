package entity

import "github.com/jinzhu/gorm"

type Manager struct {
	gorm.Model
	ManagerId uint    `gorm:"column:manager_id"`
	FirstName uint    `gorm:"column:first_name"`
	LastName  uint    `gorm:"column:last_name"`
	Items     []*Item `gorm:"foreignkey:ManagerId"`
}

func (purchase *Manager) TableName() string {
	return "managers"
}
