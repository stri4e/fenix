package payload

type Specification struct {
	Id          uint   `json:"id" example:"1"`
	Name        string `json:"name" example:"Phone"`
	Description string `json:"description" example:"This is good product"`
}
