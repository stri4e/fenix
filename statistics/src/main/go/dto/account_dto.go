package dto

import (
	"time"
)

// AccountDto swagger:model
type AccountDto struct {
	Id        uint           `json:"id" example:"1"`
	UserId    uint           `json:"userId" example:"11"`
	CreatedAt time.Time      `json:"createAt" example:"2019-11-09T21:21:46+00:00"`
	Purchases []*PurchaseDto `json:"purchases"`
	Logins    []*LoginDto    `json:"logins"`
	Views     []*ViewDto     `json:"views"`
}
