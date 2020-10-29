package entity

type Purchase struct {
	Id        uint   `gorm:"column:id"`
	ManagerId string `gorm:"column:manager_id"`
	OrderId   uint   `gorm:"column:order_id"`
	Status    string `gorm:"column:status"`
}

func (purchase *Purchase) TableName() string {
	return "purchases"
}
