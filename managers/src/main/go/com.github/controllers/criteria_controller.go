package controllers

import (
	"../dto"
	"../services"
)

type CriteriaController struct {
	service *services.CriteriaService
}

func NewCriteriaController(service *services.CriteriaService) *CriteriaController {
	return &CriteriaController{service: service}
}

// SaveCriteriaToFilter godoc
// @Summary Save criteria to filter
// @Description Save criteria to filter
// @Tags criteria
// @Accept  json
// @Produce  json
// @Param filterId path integer true "Filter Id"
// @Param account body dto.CriteriaDto true "Create filter"
// @Success 200 {object} dto.CriteriaDto
// @Failure 400
// @Failure 403
// @Router /v1/criteria/to/filters/{filterId} [post]
func (controller *CriteriaController) SaveToFilter(filterId uint, criteria *dto.CriteriaDto) (*dto.CriteriaDto, error) {
	return controller.service.CreateToFilter(filterId, criteria)
}

// SaveCriteriaToProduct godoc
// @Summary Save criteria to product
// @Description Save criteria to product
// @Tags criteria
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product Id"
// @Param account body integer true "Create criteria"
// @Success 200 {object} dto.CriteriaDto
// @Failure 400
// @Failure 403
// @Router /v1/criteria/to/products/{productId} [post]
func (controller *CriteriaController) SaveToProducts(productId uint, criteria []uint) error {
	return controller.service.CreateToProducts(productId, criteria)
}

// FindCriteriaById godoc
// @Summary Get criteria
// @Description Get criteria
// @Tags criteria
// @Accept  json
// @Produce  json
// @Param criteriaId path integer true "Criteria ID"
// @Success 200 {object} dto.CriteriaDto
// @Failure 400
// @Failure 403
// @Router /v1/criteria/{criteriaId} [get]
func (controller *CriteriaController) FindById(id uint) (*dto.CriteriaDto, error) {
	return controller.service.ReadById(id)
}

// UpdateCriteria godoc
// @Summary Update criteria
// @Description Update criteria information
// @Tags filters
// @Accept  json
// @Produce  json
// @Param account body dto.CriteriaDto true "Update criteria"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/criteria [put]
func (controller *CriteriaController) Update(criteria *dto.CriteriaDto) error {
	return controller.service.Update(criteria)
}

// UpdateInProduct godoc
// @Summary Update criteria
// @Description Update criteria information
// @Tags criteria
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Param criteriaId path integer true "Criteria ID"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/criteria/in/product/{productId}/{criteriaId} [put]
func (controller *CriteriaController) UpdateInProduct(productId uint, criteriaId uint) error {
	return controller.service.UpdateInProduct(productId, criteriaId)
}

// UpdateInFilter godoc
// @Summary Update criteria
// @Description Update criteria information
// @Tags criteria
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Param criteriaId path integer true "Criteria ID"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/criteria/in/filters/{filterId}/{criteriaId} [put]
func (controller *CriteriaController) UpdateInFilters(filterId uint, criteriaId uint) error {
	return controller.service.UpdateInFilters(filterId, criteriaId)
}
