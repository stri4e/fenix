package dto

// LoginDto swagger:model
type LoginDto struct {
	Id        uint      `json:"id" example:"1"`
	UserId    int64     `json:"userId,omitempty" example:"12"`
	Device    string    `json:"device" example:"phone"`
	Location  string    `json:"location" example:"192.168.1.1"`
}
