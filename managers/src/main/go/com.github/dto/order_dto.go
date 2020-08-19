package dto

type OrderDto struct {
	Id       uint         `json:"id" example:"1"`
	Customer CustomerDto  `json:"customer" example:"customer"`
	Products []ProductDto `json:"product" example:"products"`
	Amount   float64      `json:"amount" example:"amount"`
	Status   string       `json:"status" example:"status"`
}
