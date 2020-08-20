package services

import (
	"../entity"
	"../repository"
)

type ItemService struct {
	repository *repository.ItemRepository
}

func NewItemService(repository *repository.ItemRepository) *ItemService {
	return &ItemService{repository: repository}
}

func (service *ItemService) CreateItem(item *entity.Item) (*entity.Item, error) {
	return service.repository.SaveItem(item)
}

func (service *ItemService) UpdateStatus(orderId uint, status string) error {
	return service.repository.UpdateStatus(orderId, status)
}
