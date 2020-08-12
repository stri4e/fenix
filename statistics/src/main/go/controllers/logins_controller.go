package controllers

import (
	"../dto"
	"../services"
	"../utils"
)

type LoginsController struct {
	service *services.LoginService
}

func NewLoginsController(service *services.LoginService) *LoginsController {
	return &LoginsController{service: service}
}

// GetByAccountId godoc
// @Summary Get details of user logins
// @Description Get details of user logins
// @Tags logins
// @Accept  json
// @Produce  json
// @Param accountId path integer true "Account ID"
// @Success 200 {object} dto.LoginDto
// @Router /v1/logins/{accountId} [get]
func (controller *LoginsController) GetByAccountId(accountId uint) (*dto.LoginDto, error) {
	login, err := controller.service.ReadByAccountId(accountId)
	if err != nil {
		return nil, err
	}
	return utils.FromLogin(login), nil
}

// CreateLogin godoc
// @Summary Create a new login
// @Description Create a new login
// @Tags logins
// @Accept  json
// @Produce  json
// @Param account body dto.LoginDto true "Create login"
// @Success 200 {object} dto.LoginDto
// @Router /v1/logins [post]
func (controller *LoginsController) CreateLogin(payload *dto.LoginDto) (*dto.LoginDto, error) {
	login := utils.ToLogin(payload)
	data, err := controller.service.CreateLogin(login)
	if err != nil {
		return nil, err
	}
	return utils.FromLogin(data), nil
}
