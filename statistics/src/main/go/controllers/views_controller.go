package controllers

import (
	"../dto"
	"../services"
	"../utils"
)

type ViewsController struct {
	service *services.ViewService
}

func NewViewsController(service *services.ViewService) *ViewsController {
	return &ViewsController{service: service}
}

// GetByAccountId godoc
// @Summary Get details of user views
// @Description Get details of user views
// @Tags views
// @Accept  json
// @Produce  json
// @Param accountId path integer true "Account ID"
// @Success 200 {object} dto.ViewDto
// @Router /v1/views/{accountId} [get]
func (controller *ViewsController) GetByAccountId(accountId uint) ([]*dto.ViewDto, error) {
	views, err := controller.service.ReadByAccountId(accountId)
	if err != nil {
		return nil, err
	}
	return utils.FromViewsArray(views), nil
}

// CreateView godoc
// @Summary Create a new views
// @Description Create a new views
// @Tags views
// @Accept  json
// @Produce  json
// @Param view body dto.ViewDto true "Create view"
// @Success 200 {object} dto.ViewDto
// @Router /v1/views [post]
func (controller *ViewsController) CreateView(payload *dto.ViewDto) (*dto.ViewDto, error) {
	views := utils.ToView(payload)
	result, err := controller.service.CreateView(views)
	if err != nil {
		return nil, err
	}
	return utils.FromView(result), nil
}
