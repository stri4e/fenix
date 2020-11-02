package controllers

import (
	"managers/src/main/go/com.github/dto"
	"managers/src/main/go/com.github/services"
)

type SubcategoryController struct {
	service *services.SubcategoryService
}

func NewSubcategoryController(service *services.SubcategoryService) *SubcategoryController {
	return &SubcategoryController{service: service}
}

// SaveSubcategory godoc
// @Summary Save subcategory
// @Description Save subcategory
// @Tags subcategory
// @Accept  json
// @Produce  json
// @Param subcategoryName path string true "Subcategory Name"
// @Param account body dto.SubcategoryDto true "Create subcategory"
// @Success 200 {object} dto.SubcategoryDto
// @Failure 400
// @Failure 403
// @Router /v1/subcategory/{categoryName} [post]
func (controller *SubcategoryController) Save(categoryName string, subcategory *dto.SubcategoryDto) (*dto.SubcategoryDto, error) {
	return controller.service.Create(categoryName, subcategory)
}

// FindSubcategoryByName godoc
// @Summary Get subcategory
// @Description Get subcategory
// @Tags subcategory
// @Accept  json
// @Produce  json
// @Param subcategoryName path string true "Subcategory Name"
// @Success 200 {object} dto.SubcategoryDto
// @Failure 400
// @Failure 403
// @Router /v1/subcategory/{subcategoryName} [get]
func (controller *SubcategoryController) ReadByName(name string) (*dto.SubcategoryDto, error) {
	return controller.service.ReadByName(name)
}

// UpdateSubcategory godoc
// @Summary Update subcategory
// @Description Update subcategory information
// @Tags subcategory
// @Accept  json
// @Produce  json
// @Param account body dto.SubcategoryDto true "Update subcategory"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/subcategory [put]
func (controller *SubcategoryController) Update(subcategory *dto.SubcategoryDto) error {
	return controller.service.Update(subcategory)
}
