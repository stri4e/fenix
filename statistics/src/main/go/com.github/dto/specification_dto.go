package dto

type SpecificationDto struct {
	Id          uint   `json:"id" example:"1"`
	Name        string `json:"name" example:"specificationName"`
	Description string `json:"description" example:"This is specification description"`
}
