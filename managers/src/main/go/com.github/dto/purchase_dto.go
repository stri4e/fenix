package dto

// PurchaseDto swagger:model
type PurchaseDto struct {
	OrderId uint   `json:"orderId" example:"1"`
	Status  string `json:"status" example:"open or close"`
}
