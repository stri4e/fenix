package services

import (
	"../entity"
	"../repository"
)

type PurchaseService struct {
	repo *repository.PurchaseRepo
}

func NewPurchaseService(repo *repository.PurchaseRepo) *PurchaseService {
	return &PurchaseService{repo: repo}
}

func (service *PurchaseService) ReadByUserId(userId uint) ([]*entity.Purchase, error) {
	return service.repo.FindByUserId(userId)
}

func (service *PurchaseService) ReadBetweenTime(start string, end string) ([]*entity.Purchase, error) {
	return service.repo.FindBetweenTime(start, end)
}

func (service *PurchaseService) ReadUserId(orderId uint) (uint, error) {
	return service.repo.FindUserId(orderId)
}

func (service *PurchaseService) CreatePurchase(purchase *entity.Purchase) (*entity.Purchase, error) {
	return service.repo.Save(purchase)
}

func (service *PurchaseService) UpdatePurchase(orderId uint, status string, manager *entity.Manager) error {
	return service.repo.UpdatePurchase(orderId, status, manager)
}
