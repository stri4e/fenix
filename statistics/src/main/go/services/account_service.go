package services

import (
	"../entity"
	"../repository"
)

type AccountService struct {
	repo *repository.AccountRepo
}

func NewAccountService(repo *repository.AccountRepo) *AccountService {
	return &AccountService{repo: repo}
}

func (service AccountService) ReadByUserId(userId uint) (*entity.Account, error) {
	return service.repo.FindByUserId(userId)
}

func (service AccountService) CreateAccount(account *entity.Account) (*entity.Account, error) {
	return service.repo.SaveAccount(account)
}
