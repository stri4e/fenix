package dto

type FilterDto struct {
	Id       uint          `json:"id" example:"1"`
	Title    string        `json:"title" example:"filter_title"`
	Criteria []CriteriaDto `json:"criteria"`
}
