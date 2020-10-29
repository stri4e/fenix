package controllers

import (
	"../dto"
	"../services"
)

type ProductController struct {
	categoryService *services.CategoryService
	productService  *services.ProductService
}

func NewProductController(
	categoryService *services.CategoryService,
	productService *services.ProductService) *ProductController {
	return &ProductController{
		categoryService: categoryService,
		productService:  productService,
	}
}

// SaveProduct godoc
// @Summary Save product
// @Description Save product
// @Tags products
// @Accept  json
// @Produce  json
// @Param categoryName path string true "Category Name"
// @Param account body dto.ProductDto true "Create products"
// @Success 200 {object} dto.ProductDto
// @Failure 400
// @Failure 403
// @Router /v1/products/{subcategoryName} [post]
func (controller *ProductController) SaveProduct(subcategoryName string, payload *dto.ProductDto) (*dto.ProductDto, error) {
	return controller.productService.Create(subcategoryName, payload)
}

// FindById godoc
// @Summary Get product
// @Description Get product
// @Tags products
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Success 200 {object} dto.ProductDto
// @Failure 400
// @Failure 403
// @Router /v1/products/{productId} [get]
func (controller *ProductController) FindById(productId uint) (*dto.ProductDto, error) {
	return controller.productService.ReadById(productId)
}

// FindAllUnPublish godoc
// @Summary Get product
// @Description Get product
// @Tags products
// @Accept  json
// @Produce  json
// @Success 200 {object} dto.ProductDto
// @Failure 400
// @Failure 403
// @Router /v1/products/un-publish [get]
func (controller *ProductController) FindAllUnPublish() (*[]dto.ProductDto, error) {
	return controller.productService.ReadAllUnPublish()
}

// UpdateProduct godoc
// @Summary Update Product
// @Description Update product information
// @Tags products
// @Accept  json
// @Produce  json
// @Param account body dto.ProductDto true "Update product"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/products [put]
func (controller *ProductController) UpdateProduct(payload *dto.ProductDto) error {
	return controller.productService.UpdateProduct(payload)
}

// UpdateStatusProduct godoc
// @Summary Change Product
// @Description Change status product
// @Tags products
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Param status path string true "Product Status"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/products/{productId}/{status} [delete]
func (controller *ProductController) UpdateStatusProduct(productId uint, status string) error {
	return controller.productService.UpdateStatusProduct(productId, status)
}
