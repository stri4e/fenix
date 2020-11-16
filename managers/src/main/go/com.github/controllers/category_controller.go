package controllers

import (
	"managers/src/main/go/com.github/dto"
	"managers/src/main/go/com.github/services"
)

type CategoryController struct {
	categoryService *services.CategoryService
}

func NewCategoryController(categoryService *services.CategoryService) *CategoryController {
	return &CategoryController{categoryService: categoryService}
}

// FindByCategoryName godoc
// @Summary Get category by category name
// @Description Get category by category name
// @Tags products category
// @Accept  json
// @Produce  json
// @Param categoryName path string true "Category Name"
// @Success 200 {object} dto.CategoryDto
// @Failure 400
// @Failure 403
// @Router /v1/category/{categoryName} [get]
func (controller *CategoryController) FindByCategoryName(categoryName string) (*dto.CategoryDto, error) {
	return controller.categoryService.ReadByName(categoryName)
}

// SaveCategory godoc
// @Summary Save category
// @Description Save product category
// @Tags products category
// @Accept  json
// @Produce  json
// @Param account body dto.CategoryDto true "Create category"
// @Success 200 {object} dto.CategoryDto
// @Failure 400
// @Failure 403
// @Router /v1/category [post]
func (controller *CategoryController) SaveCategory(payload *dto.CategoryDto) (*dto.CategoryDto, error) {
	return controller.categoryService.CreateCategory(payload)
}

// UpdateCategory godoc
// @Summary Update Category
// @Description Update category
// @Tags products category
// @Accept  json
// @Produce  json
// @Param account body dto.CategoryDto true "Create category"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/category [put]
func (controller *CategoryController) UpdateCategory(payload *dto.CategoryDto) error {
	return controller.categoryService.UpdateCategory(payload)
}
