package services

import (
	"../repository"
)

type ManagersService struct {
	repo *repository.ManagersRepository
}

func NewManagersService(repo *repository.ManagersRepository) *ManagersService {
	return &ManagersService{repo: repo}
}
