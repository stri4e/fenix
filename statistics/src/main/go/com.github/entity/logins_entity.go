package entity

import "github.com/jinzhu/gorm"

type Login struct {
	gorm.Model
	UserId   string `gorm:"column:user_id"`
	Device   string `gorm:"column:device"`
	Location string `gorm:"column:location"`
}

func (product *Login) TableName() string {
	return "logins"
}
