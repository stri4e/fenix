package dto

// OrderDto swagger:model
type OrderDto struct {
	Id       uint         `json:"id" example:"1"`
	Customer CustomerDto  `json:"customer"`
	Products []ProductDto `json:"product"`
	Amount   float64      `json:"amount" example:"1.99"`
	Status   string       `json:"status" example:"status"`
	Delivery DeliveryDto  `json:"delivery"`
}
