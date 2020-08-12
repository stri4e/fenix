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

func (service *PurchaseService) ReadByAccountId(accountId uint) ([]*entity.Purchase, error) {
	return service.repo.FindByAccountId(accountId)
}

func (service *PurchaseService) CreatePurchase(purchase *entity.Purchase) (*entity.Purchase, error) {
	return service.repo.Save(purchase)
}
