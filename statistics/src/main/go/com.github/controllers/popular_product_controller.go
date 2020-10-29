package controllers

import (
	"../entity"
	"../services"
)

type PopularProductController struct {
	service *services.PopularProductService
}

func NewPopularProductController(service *services.PopularProductService) *PopularProductController {
	return &PopularProductController{service: service}
}

func (controller *PopularProductController) UpdatePercentView(productId uint) error {
	total, err := controller.service.TotalSumView()
	if err != nil {
		return err
	}
	var coefficient = float32(total) / 100
	product, err := controller.service.ReadOrCreate(&entity.PopularProduct{ProductId: productId})
	if err != nil {
		return err
	}
	var percent = float32(product.ViewCount) / coefficient
	viewCount := product.ViewCount + 1
	return controller.service.UpdatePercentView(viewCount, percent, productId)
}

func (controller *PopularProductController) UpdatePercentBought(productIds []uint) error {
	total, err := controller.service.TotalSumBought()
	if err != nil {
		return err
	}
	products, err := controller.service.ReadByIds(productIds)
	if err != nil {
		return err
	}
	for _, product := range products {
		var coefficient = float32(total) / 100
		var percent = float32(product.BoughtCount) / coefficient
		boughtCount := product.BoughtCount + 1
		err = controller.service.UpdatePercentBought(boughtCount, percent, product.ProductId)
		if err != nil {
			return err
		}
	}
	return nil
}
