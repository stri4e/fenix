package services

import (
	"../entity"
	"../repository"
)

type LoginService struct {
	repo *repository.LoginRepo
}

func NewLoginService(repo *repository.LoginRepo) *LoginService {
	return &LoginService{repo: repo}
}

func (service *LoginService) ReadByAccountId(accountId uint) (*entity.Login, error) {
	return service.repo.FindByAccountId(accountId)
}

func (service *LoginService) CreateLogin(login *entity.Login) (*entity.Login, error) {
	return service.repo.Save(login)
}
