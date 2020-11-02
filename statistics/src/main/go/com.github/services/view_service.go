package services

import (
	"statistics/src/main/go/com.github/entity"
	"statistics/src/main/go/com.github/repository"
)

type ViewService struct {
	repo *repository.ViewRepo
}

func NewViewService(repo *repository.ViewRepo) *ViewService {
	return &ViewService{repo: repo}
}

func (service ViewService) ReadByUserId(userId string) ([]*entity.View, error) {
	return service.repo.FindByUserId(userId)
}

func (service ViewService) ReadBetweenTime(start string, end string) ([]*entity.View, error) {
	return service.repo.FindBetweenTime(start, end)
}

func (service ViewService) CreateView(data *entity.View) error {
	return service.repo.Save(data)
}
