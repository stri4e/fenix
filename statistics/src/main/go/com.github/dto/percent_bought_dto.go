package dto

type PercentBoughtDto struct {
	ProductId     uint    `json:"productId" example:"1"`
	PercentBought float32 `json:"percentBought" example:"1.11"`
}
