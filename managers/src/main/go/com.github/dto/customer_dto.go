package dto

type CustomerDto struct {
	Id              uint   `json:"id" example:"1"`
	PurchaseId      uint   `json:"purchaseId,omitempty" example:"11"`
	CustomerName    string `json:"customerName"`
	CustomerAddress string `json:"customerAddress"`
	CustomerEmail   string `json:"customerEmail"`
	CustomerPhone   string `json:"customerPhone"`
}
