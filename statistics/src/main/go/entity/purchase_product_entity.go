package entity

import (
	"time"
)

type PurchaseProduct struct {
	Id           uint             `gorm:"column:id"`
	CreatedAt    time.Time        `gorm:"column:created_at"`
	PurchaseId   uint             `gorm:"column:purchase_id"`
	Name         string           `gorm:"column:name"`
	Price        float64          `gorm:"column:price"`
	Quantity     int              `gorm:"column:quantity"`
	Description  string           `gorm:"column:description"`
	PreviewImage string           `gorm:"column:preview_image"`
	Images       []*PurchaseImage `gorm:"foreignkey:ProductId"`
}

func (product *PurchaseProduct) TableName() string {
	return "purchase_products"
}
