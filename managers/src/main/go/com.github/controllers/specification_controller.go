package controllers

import (
	"../dto"
	"../services"
	"../utils"
)

type SpecificationController struct {
	productService *services.ProductService
	specService    *services.SpecificationService
}

func NewSpecificationController(productService *services.ProductService, specService *services.SpecificationService) *SpecificationController {
	return &SpecificationController{productService: productService, specService: specService}
}

// SaveSpecification godoc
// @Summary Save specification
// @Description Save specification
// @Tags specifications
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Param account body dto.SpecificationDto true "Create products"
// @Success 200 {object} dto.ProductDto
// @Failure 400
// @Failure 403
// @Router /v1/specifications/{productId} [post]
func (controller *SpecificationController) SaveSpecification(productId uint, payload *dto.SpecificationDto) (*dto.SpecificationDto, error) {
	product, err := controller.productService.ReadById(productId)
	if err != nil {
		return nil, err
	}
	data := utils.ToSpecification(payload)
	specification, err := controller.specService.Create(data)
	if err != nil {
		return nil, err
	}
	product.Specifications = append(product.Specifications, *specification)
	err = controller.productService.UpdateProduct(product)
	if err != nil {
		return nil, err
	}
	return utils.FromSpecification(specification), nil
}

// FindSpecById godoc
// @Summary Get specification
// @Description Get specification
// @Tags specification
// @Accept  json
// @Produce  json
// @Param specificationId path integer true "Specification ID"
// @Success 200 {object} dto.SpecificationDto
// @Failure 400
// @Failure 403
// @Router /v1/specifications/{specificationId} [get]
func (controller *SpecificationController) FindSpecById(specificationId uint) (*dto.SpecificationDto, error) {
	specification, err := controller.specService.ReadById(specificationId)
	if err != nil {
		return nil, err
	}
	return utils.FromSpecification(specification), nil
}

// UpdateSpecification godoc
// @Summary Update Specification
// @Description Update specification information
// @Tags specification
// @Accept  json
// @Produce  json
// @Param account body dto.SpecificationDto true "Update specification"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/specifications [put]
func (controller *SpecificationController) UpdateSpecification(payload *dto.SpecificationDto) error {
	return controller.specService.Update(utils.ToSpecification(payload))
}
