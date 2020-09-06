package services

import (
	"../entity"
	"../repository"
)

type PurchaseService struct {
	repository *repository.PurchaseRepository
}

func NewPurchaseService(repository *repository.PurchaseRepository) *PurchaseService {
	return &PurchaseService{repository: repository}
}

func (service *PurchaseService) CreatePurchase(purchase *entity.Purchase) (*entity.Purchase, error) {
	return service.repository.SavePurchase(purchase)
}

func (service *PurchaseService) UpdateStatus(orderId uint, status string) error {
	return service.repository.UpdateStatus(orderId, status)
}

func (service *PurchaseService) FindPurchaseByOrderId(orderId uint) (*entity.Purchase, error) {
	return service.repository.FindByOrderId(orderId)
}
