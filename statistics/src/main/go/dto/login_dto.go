package dto

import (
	"time"
)

// LoginDto swagger:model
type LoginDto struct {
	Id        uint      `json:"id" example:"1"`
	AccountId int64     `json:"accountId,omitempty" example:"12"`
	CreatedAt time.Time `json:"createAt" example:"2019-11-09T21:21:46+00:00"`
	Ip        string    `json:"ip" example:"192.168.1.1"`
	Device    string    `json:"device" example:"phone"`
	Location  string    `json:"location" example:"Zimbabve"`
}
