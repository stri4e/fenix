package controllers

import (
	"../dto"
	"../services"
	"../utils"
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
// @Router /v1/products/{categoryName} [post]
func (controller *ProductController) SaveProduct(categoryName string, payload *dto.ProductDto) (*dto.ProductDto, error) {
	category, err := controller.categoryService.GetByCategoryName(categoryName)
	if err != nil {
		return nil, err
	}
	product := utils.ToProduct(payload)
	product.Category = category
	result, err := controller.productService.CreateProduct(product)
	if err != nil {
		return nil, err
	}
	return utils.FromProduct(result), nil
}

// FindById godoc
// @Summary Get product
// @Description Get product
// @Tags product
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Success 200 {object} dto.ProductDto
// @Failure 400
// @Failure 403
// @Router /v1/products/{productId} [get]
func (controller *ProductController) FindById(productId uint) (*dto.ProductDto, error) {
	product, err := controller.productService.ReadById(productId)
	if err != nil {
		return nil, err
	}
	return utils.FromProduct(product), nil
}

// FindAllUnPublish godoc
// @Summary Get product
// @Description Get product
// @Tags product
// @Accept  json
// @Produce  json
// @Success 200 {object} dto.ProductDto
// @Failure 400
// @Failure 403
// @Router /v1/products/un-publish [get]
func (controller *ProductController) FindAllUnPublish() ([]*dto.ProductDto, error) {
	products, err := controller.productService.ReadAllUnPublish()
	if err != nil {
		return nil, err
	}
	var result []*dto.ProductDto
	for _, p := range *products {
		result = append(result, utils.FromProduct(&p))
	}
	return result, nil
}

// UpdateProduct godoc
// @Summary Update Product
// @Description Update product information
// @Tags product
// @Accept  json
// @Produce  json
// @Param account body dto.ProductDto true "Update product"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/products [put]
func (controller *ProductController) UpdateProduct(payload *dto.ProductDto) error {
	return controller.productService.UpdateProduct(utils.ToProduct(payload))
}

// UpdateStatusProduct godoc
// @Summary Change Product
// @Description Change status product
// @Tags product
// @Accept  json
// @Produce  json
// @Param productId path integer true "Product ID"
// @Param status path integer true "Product Status"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/products/{productId}/{status} [delete]
func (controller *ProductController) UpdateStatusProduct(productId uint, status string) error {
	return controller.productService.UpdateStatusProduct(productId, status)
}
