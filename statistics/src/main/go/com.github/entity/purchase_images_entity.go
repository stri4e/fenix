package entity

type PurchaseImage struct {
	Id        uint   `gorm:"column:id"`
	ProductId uint   `gorm:"column:product_id"`
	Img       string `gorm:"column:image"`
}

func (image *PurchaseImage) TableName() string {
	return "purchase_images"
}
