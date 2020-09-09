package payload

type Comment struct {
	Id          uint   `json:"id" example:"1"`
	Name        string `json:"name" example:"comment author"`
	Description string `json:"description" example:"This is specification description"`
}
