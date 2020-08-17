package entity

type ViewedProduct struct {
	Id           uint           `gorm:"column:id"`
	ProductId    uint           `gorm:"column:product_id"`
	ViewId       uint           `gorm:"column:view_id"`
	Name         string         `gorm:"column:name"`
	Price        float64        `gorm:"column:price"`
	Quantity     int            `gorm:"column:quantity"`
	Description  string         `gorm:"column:description"`
	PreviewImage string         `gorm:"column:preview_image"`
	Images       []*ViewedImage `gorm:"foreignkey:ProductId"`
}

func (product *ViewedProduct) TableName() string {
	return "viewed_product"
}
