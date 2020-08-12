package dto

// ProductDto swagger:model
type ProductDto struct {
	Id           uint     `json:"id" example:"1"`
	Name         string   `json:"name" example:"Phone"`
	Price        float64  `json:"price" example:"1.11"`
	Quantity     int      `json:"quantity" example:"10"`
	Description  string   `json:"description" example:"This is phone"`
	PreviewImage string   `json:"previewImage" example:"image"`
	Images       []string `json:"images"`
}
