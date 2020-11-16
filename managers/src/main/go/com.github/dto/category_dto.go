package dto

type CategoryDto struct {
	Id            uint             `json:"id" example:"1"`
	Name          string           `json:"name" example:"Phone"`
	Subcategories []SubcategoryDto `json:"subcategories"`
}
