package services

import (
	"../entity"
	"../repository"
)

type ManagersService struct {
	repo *repository.ManagersRepository
}

func NewManagersService(repo *repository.ManagersRepository) *ManagersService {
	return &ManagersService{repo: repo}
}

func (service *ManagersService) FirstOrCreateManager(manager *entity.Manager) (*entity.Manager, error) {
	return service.repo.FirstOrCreateManager(manager)
}
