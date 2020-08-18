package entity

type Item struct {
	Id        uint   `gorm:"column:id"`
	ManagerId uint   `gorm:"column:manager_id"`
	OrderId   uint   `gorm:"column:order_id"`
	Status    string `gorm:"column:status_id"`
}

func (purchase *Item) TableName() string {
	return "items"
}
