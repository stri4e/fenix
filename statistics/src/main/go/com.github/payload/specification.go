package payload

type Specification struct {
	Id          uint   `json:"id" example:"1"`
	Name        string `json:"name" example:"specification name"`
	Description string `json:"description" example:"This is specification description"`
}
