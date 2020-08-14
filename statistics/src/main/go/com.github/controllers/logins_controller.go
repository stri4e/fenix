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

// FindByUserId godoc
// @Summary Get details of user logins
// @Description Get details of user logins
// @Tags logins
// @Accept  json
// @Produce  json
// @Param userId path integer true "User ID"
// @Success 200 {object} dto.LoginDto
// @Router /v1/logins/fetch/{userId} [get]
func (controller *LoginsController) FindByUserId(userId uint) ([]*dto.LoginDto, error) {
	login, err := controller.service.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	return utils.FromLoginArray(login), nil
}

// FindBetweenTime godoc
// @Summary Get details of user logins
// @Description Get details of user logins
// @Tags logins
// @Accept  json
// @Produce  json
// @Param start query string false "name search by start"
// @Param end query string false "name search by end"
// @Success 200 {object} dto.LoginDto
// @Router /v1/logins/fetch [get]
func (controller *LoginsController) FindBetweenTime(start string, end string) ([]*dto.LoginDto, error) {
	login, err := controller.service.ReadBetweenTime(start, end)
	if err != nil {
		return nil, err
	}
	return utils.FromLoginArray(login), nil
}

// CreateLogin godoc
// @Summary Create a new login
// @Description Create a new login
// @Tags logins
// @Accept  json
// @Produce  json
// @Param account body dto.LoginDto true "Create login"
// @Success 200 {object} dto.LoginDto
// @Router /v1/logins/edit [post]
func (controller *LoginsController) CreateLogin(payload *dto.LoginDto) (*dto.LoginDto, error) {
	login := utils.ToLogin(payload)
	data, err := controller.service.CreateLogin(login)
	if err != nil {
		return nil, err
	}
	return utils.FromLogin(data), nil
}
