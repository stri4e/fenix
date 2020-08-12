package entity

import (
	"time"
)

type ViewedProduct struct {
	Id           uint           `gorm:"column:id"`
	CreatedAt    time.Time      `gorm:"column:created_at"`
	PurchaseId   uint           `gorm:"column:purchase_id"`
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
