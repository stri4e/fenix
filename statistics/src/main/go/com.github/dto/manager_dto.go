package dto

// ManagerDto swagger:model
type ManagerDto struct {
	ManagerId uint   `json:"managerId,omitempty" example:"1"`
	FirstName string `json:"firstName" example:"Michal"`
	LastName  string `json:"lastName" example:"Mackonagen"`
}
