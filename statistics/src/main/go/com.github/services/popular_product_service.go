package services

import (
	"statistics/src/main/go/com.github/entity"
	"statistics/src/main/go/com.github/repository"
)

type PopularProductService struct {
	repo *repository.PopularProductRepo
}

func NewPopularProductService(repo *repository.PopularProductRepo) *PopularProductService {
	return &PopularProductService{repo: repo}
}

func (service *PopularProductService) ReadOrCreate(popular *entity.PopularProduct) (*entity.PopularProduct, error) {
	return service.repo.FirstOrCreate(popular)
}

func (service *PopularProductService) ReadByIds(productIds []uint) ([]*entity.PopularProduct, error) {
	return service.repo.FindByIds(productIds)
}

func (service *PopularProductService) TotalSumView() (int64, error) {
	return service.repo.TotalSumView()
}

func (service *PopularProductService) TotalSumBought() (int64, error) {
	return service.repo.TotalSumBought()
}

func (service *PopularProductService) UpdatePercentView(viewCount int, percentView float32, productId uint) error {
	return service.repo.UpdatePercentView(viewCount, percentView, productId)
}

func (service *PopularProductService) UpdatePercentBought(boughtCount int, percentBought float32, productId uint) error {
	return service.repo.UpdatePercentBought(boughtCount, percentBought, productId)
}
