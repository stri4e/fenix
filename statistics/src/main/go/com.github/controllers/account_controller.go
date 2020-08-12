package controllers

import (
	"../dto"
	"../services"
	"../utils"
)

type AccountsController struct {
	accountService *services.AccountService
}

func NewAccountController(service *services.AccountService) *AccountsController {
	return &AccountsController{accountService: service}
}

// GetByUserId godoc
// @Summary Get details of user account
// @Description Get details of user account
// @Tags accounts
// @Accept  json
// @Produce  json
// @Param userId path integer true "User ID"
// @Success 200 {object} dto.AccountDto
// @Router /v1/accounts/{userId} [get]
func (controller *AccountsController) GetByUserId(userId uint) (*dto.AccountDto, error) {
	account, err := controller.accountService.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	return utils.FromAccount(account), nil
}

// CreateAccount godoc
// @Summary Create a new account
// @Description Create a new account with the input payload
// @Tags accounts
// @Accept  json
// @Produce  json
// @Param account body dto.AccountDto true "Create account"
// @Success 201 {object} dto.AccountDto
// @Router /v1/accounts [post]
func (controller *AccountsController) CreateAccount(payload *dto.AccountDto) (*dto.AccountDto, error) {
	account := utils.ToAccount(payload)
	result, err := controller.accountService.CreateAccount(account)
	if err == nil {
		return utils.FromAccount(result), nil
	}
	return nil, err
}
