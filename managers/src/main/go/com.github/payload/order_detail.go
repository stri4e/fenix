package payload

type OrderDetail struct {
	Id       uint      `json:"id" example:"1"`
	Customer Customer  `json:"id" example:"customer"`
	Products []Product `json:"id" example:"products"`
	Amount   float64   `json:"id" example:"amount"`
	Status   string    `json:"id" example:"status"`
}
