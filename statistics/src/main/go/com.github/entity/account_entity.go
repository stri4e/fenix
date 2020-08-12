package entity

import (
	"time"
)

type Account struct {
	Id        uint        `gorm:"column:id"`
	CreatedAt time.Time   `gorm:"column:created_at"`
	UserId    uint        `gorm:"column:user_id"`
	Purchases []*Purchase `gorm:"foreignkey:AccountId"`
	Logins    []*Login    `gorm:"foreignkey:AccountId"`
	Views     []*View     `gorm:"foreignkey:AccountId"`
}

func (account *Account) TableName() string {
	return "account"
}
