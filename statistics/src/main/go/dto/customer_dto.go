package dto

import (
	"time"
)

// CustomerDto swagger:model
type CustomerDto struct {
	Id              uint      `json:"id" example:"1"`
	PurchaseId      uint      `json:"purchaseId,omitempty" example:"11"`
	CreatedAt       time.Time `json:"createAt" example:"2019-11-09T21:21:46+00:00"`
	CustomerName    string    `json:"customerName"`
	CustomerAddress string    `json:"customerAddress"`
	CustomerEmail   string    `json:"customerEmail"`
	CustomerPhone   string    `json:"customerPhone"`
}
