package dto

type SubcategoryDto struct {
	Id      uint         `json:"id" example:"1"`
	Name    string       `json:"name" example:"Phone"`
	Filters *[]FilterDto `json:"filters"`
}
