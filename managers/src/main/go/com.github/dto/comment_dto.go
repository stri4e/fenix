package dto

// CommentDto swagger:model
type CommentDto struct {
	Id          uint   `json:"id" example:"1"`
	Name        string `json:"name" example:"comment author"`
	Description string `json:"description" example:"This is comment description"`
}
