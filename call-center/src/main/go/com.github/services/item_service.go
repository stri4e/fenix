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
