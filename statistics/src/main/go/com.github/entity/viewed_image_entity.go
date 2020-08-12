package entity

type ViewedImage struct {
	Id        uint   `gorm:"column:id"`
	ProductId uint   `gorm:"column:product_id"`
	Img       string `gorm:"column:image"`
}

func (image *ViewedImage) TableName() string {
	return "viewed_images"
}
