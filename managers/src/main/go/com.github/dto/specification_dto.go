package dto

// SpecificationDto swagger:model
type SpecificationDto struct {
	Id          uint   `json:"id" example:"1"`
	Name        string `json:"name" example:"settings"`
	Description string `json:"description" example:"supper sensor"`
}
