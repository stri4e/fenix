package dto

// DeliveryDto swagger:model
type DeliveryDto struct {
	Id            uint   `json:"id" example:"1"`
	Type          string `json:"type,omitempty" example:"home"`
	CompanyName   string `json:"companyName,omitempty" example:"NowaPoshta"`
	BranchAddress string `json:"branchAddress,omitempty" example:"189 street"`
}
