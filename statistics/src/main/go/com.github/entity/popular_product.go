package entity

import "github.com/jinzhu/gorm"

type PopularProduct struct {
	gorm.Model
	ProductId     uint    `gorm:"column:product_id"`
	ViewCount     int     `gorm:"column:view_count"`
	BoughtCount   int     `gorm:"column:bought_count"`
	PercentView   float32 `gorm:"column:percent_view"`
	PercentBought float32 `gorm:"column:percent_bought"`
}

func (product *PopularProduct) TableName() string {
	return "popular_product"
}
