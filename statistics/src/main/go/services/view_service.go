package services

import (
	"../entity"
	"../repository"
)

type ViewService struct {
	repo *repository.ViewRepo
}

func NewViewService(repo *repository.ViewRepo) *ViewService {
	return &ViewService{repo: repo}
}

func (service ViewService) ReadByAccountId(accountId uint) ([]*entity.View, error) {
	return service.repo.FindByAccountId(accountId)
}

func (service ViewService) CreateView(data *entity.View) (*entity.View, error) {
	return service.repo.Save(data)
}
