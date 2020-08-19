package payload

type Order struct {
	Id       uint      `json:"id" example:"1"`
	Customer Customer  `json:"customer" example:"customer"`
	Products []Product `json:"product" example:"products"`
	Amount   float64   `json:"amount" example:"amount"`
	Status   string    `json:"status" example:"status"`
}
