package services

import (
	"../entity"
	"../repository"
)

type NewOrdersService struct {
	repo *repository.NewOrdersRepo
}

func NewNewOrdersService(repo *repository.NewOrdersRepo) *NewOrdersService {
	return &NewOrdersService{repo: repo}
}

func (service NewOrdersService) ReadBetweenTime(start string, end string) ([]*entity.NewOrder, error) {
	return service.repo.FindBetweenTime(start, end)
}

func (service NewOrdersService) Create(order *entity.NewOrder) error {
	return service.repo.Save(order)
}
