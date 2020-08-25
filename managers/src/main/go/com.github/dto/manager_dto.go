package dto

type ManagerDto struct {
	ManagerId uint   `json:"managerId" example:"1"`
	FirstName string `json:"firstName" example:"Michal"`
	LastName  string `json:"lastName" example:"Mackonagen"`
}
