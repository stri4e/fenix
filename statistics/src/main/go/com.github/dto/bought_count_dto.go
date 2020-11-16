package dto

type BoughtCountDto struct {
	ProductId uint    `json:"productId" example:"1"`
	Count     int `json:"count" example:"100"`
}
