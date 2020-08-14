package entity

import "github.com/jinzhu/gorm"

type Login struct {
	gorm.Model
	UserId    int64     `gorm:"column:user_id"`
	Ip        string    `gorm:"column:ip"`
	Device    string    `gorm:"column:device"`
	Location  string    `gorm:"column:location"`
}

func (product *Login) TableName() string {
	return "logins"
}
