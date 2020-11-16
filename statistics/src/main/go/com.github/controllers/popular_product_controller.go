package controllers

import (
	"statistics/src/main/go/com.github/dto"
	"statistics/src/main/go/com.github/entity"
	"statistics/src/main/go/com.github/services"
)

type PopularProductController struct {
	popularService *services.PopularProductService
	productService *services.ProductService
}

func NewPopularProductController(service *services.PopularProductService) *PopularProductController {
	return &PopularProductController{popularService: service}
}

func (controller *PopularProductController) UpdatePercentView(productId uint) error {
	total, err := controller.popularService.TotalSumView()
	if err != nil {
		return err
	}
	var coefficient = float32(total) / 100
	product, err := controller.popularService.ReadOrCreate(&entity.PopularProduct{ProductId: productId})
	if err != nil {
		return err
	}
	var percent = float32(product.ViewCount) / coefficient
	viewCount := product.ViewCount + 1
	return controller.popularService.UpdatePercentView(viewCount, percent, productId)
}

func (controller *PopularProductController) UpdatePercentBought(productIds []uint) error {
	total, err := controller.popularService.TotalSumBought()
	if err != nil {
		return err
	}
	var percentsBought []dto.BoughtCountDto
	for _, id := range productIds {
		product, err := controller.popularService.ReadOrCreate(&entity.PopularProduct{ProductId: id})
		if err != nil {
			return err
		}
		var coefficient = float32(total) / 100
		boughtCount := product.BoughtCount + 1
		var percent = float32(boughtCount) / coefficient
		percentsBought = append(percentsBought, dto.BoughtCountDto{ProductId: id, Count: boughtCount})
		err = controller.popularService.UpdatePercentBought(boughtCount, percent, product.ProductId)
		if err != nil {
			return err
		}
	}
	return controller.productService.UpdatePercentsBought(&percentsBought)
}
