package entity

import (
	"time"
)

type Login struct {
	Id        uint      `gorm:"column:id"`
	CreatedAt time.Time `gorm:"column:created_at"`
	AccountId int64     `gorm:"column:account_id"`
	Ip        string    `gorm:"column:ip"`
	Device    string    `gorm:"column:device"`
	Location  string    `gorm:"column:location"`
}

func (product *Login) TableName() string {
	return "logins"
}
