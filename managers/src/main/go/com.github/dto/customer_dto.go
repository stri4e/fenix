package dto

// CustomerDto swagger:model
type CustomerDto struct {
	Id              uint   `json:"id" example:"1"`
	PurchaseId      uint   `json:"purchaseId,omitempty" example:"11"`
	CustomerName    string `json:"customerName" example:"Julvern"`
	CustomerAddress string `json:"customerAddress" example:"Brodvey"`
	CustomerEmail   string `json:"customerEmail" example:"julvern@gmail.com"`
	CustomerPhone   string `json:"customerPhone" example:"+780466869"`
}
