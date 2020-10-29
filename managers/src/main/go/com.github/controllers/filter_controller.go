package controllers

import (
	"../dto"
	"../services"
)

type FilterController struct {
	service *services.FilterService
}

func NewFilterController(service *services.FilterService) *FilterController {
	return &FilterController{service: service}
}

// SaveFilter godoc
// @Summary Save filters
// @Description Save filters
// @Tags filters
// @Accept  json
// @Produce  json
// @Param subcategoryName path string true "Subcategory Name"
// @Param account body dto.FilterDto true "Create filter"
// @Success 200 {object} dto.FilterDto
// @Failure 400
// @Failure 403
// @Router /v1/filters/{subcategoryName} [post]
func (controller *FilterController) Save(subcategoryName string, filter *dto.FilterDto) (*dto.FilterDto, error) {
	return controller.service.Create(subcategoryName, filter)
}

// FindFilerById godoc
// @Summary Get filters
// @Description Get filters
// @Tags filters
// @Accept  json
// @Produce  json
// @Param filterId path integer true "Filter ID"
// @Success 200 {object} dto.FilterDto
// @Failure 400
// @Failure 403
// @Router /v1/filters/{filterId} [get]
func (controller *FilterController) FindById(filterId uint) (*dto.FilterDto, error) {
	return controller.service.ReadById(filterId)
}

// UpdateFilter godoc
// @Summary Update filters
// @Description Update filters information
// @Tags filters
// @Accept  json
// @Produce  json
// @Param account body dto.FilterDto true "Update filter"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/filters [put]
func (controller *FilterController) Update(filter *dto.FilterDto) error {
	return controller.service.Update(filter)
}
