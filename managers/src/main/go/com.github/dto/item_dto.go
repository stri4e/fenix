package dto

// ItemDto swagger:model
type ItemDto struct {
	OrderId uint   `json:"orderId" example:"1"`
	Status  string `json:"status" example:"open or close"`
}
