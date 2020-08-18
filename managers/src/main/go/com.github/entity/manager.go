package entity

import "github.com/jinzhu/gorm"

type Manager struct {
	gorm.Model
	ManagerId uint    `gorm:"column:manager_id"`
	FirstName string  `gorm:"column:first_name"`
	LastName  string  `gorm:"column:last_name"`
	Items     []*Item `gorm:"foreignkey:ManagerId"`
}

func (purchase *Manager) TableName() string {
	return "managers"
}
