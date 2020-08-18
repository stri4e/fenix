package dto

type ItemDto struct {
	OrderID uint   `json:"orderId" example:"1"`
	Status  string `json:"status" example:"open or close"`
}
