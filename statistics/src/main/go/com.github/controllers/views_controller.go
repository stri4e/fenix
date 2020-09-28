package controllers

import (
	"../dto"
	"../entity"
	"../services"
	"../utils"
)

type ViewsController struct {
	viewService    *services.ViewService
	productService *services.ProductService
}

func NewViewsController(viewService *services.ViewService, productService *services.ProductService) *ViewsController {
	return &ViewsController{viewService: viewService, productService: productService}
}

// FindByUserId godoc
// @Summary Get details of user views
// @Description Get details of user views
// @Tags views
// @Accept  json
// @Produce  json
// @Param userId path integer true "User ID"
// @Success 200 {object} dto.ProductDto
// @Router /v1/views/fetch/{userId} [get]
func (controller *ViewsController) FindByUserId(userId uint) ([]*dto.ProductDto, error) {
	views, err := controller.viewService.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	productIds := utils.FromViews(views)
	products, err := controller.productService.GetProducts(productIds)
	if err != nil {
		return nil, err
	}
	return utils.FromProducts(products), nil
}

// FindBetweenTime godoc
// @Summary Get details of user views
// @Description Get details of user views
// @Tags views
// @Accept  json
// @Produce  json
// @Param start query string false "name search by start"
// @Param end query string false "name search by end"
// @Success 200 {object} dto.ProductDto
// @Router /v1/views/fetch [get]
func (controller *ViewsController) FindBetweenTime(start string, end string) ([]*dto.ProductDto, error) {
	views, err := controller.viewService.ReadBetweenTime(start, end)
	if err != nil {
		return nil, err
	}
	productIds := utils.FromViews(views)
	products, err := controller.productService.GetProducts(productIds)
	if err != nil {
		return nil, err
	}
	return utils.FromProducts(products), nil
}

// FindViews godoc
// @Summary Get details of user views
// @Description Get details of user views
// @Tags views
// @Accept  json
// @Produce  json
// @Param Authorization header string true "Bearer"
// @Success 200 {object} dto.ProductDto
// @Router /v1/views [get]
func (controller *ViewsController) FindViews(userId uint) ([]*dto.ProductDto, error) {
	views, err := controller.viewService.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	productIds := utils.FromViews(views)
	products, err := controller.productService.GetProducts(productIds)
	if err != nil {
		return nil, err
	}
	return utils.FromProducts(products), nil
}

// CreateView godoc
// @Summary Create a new views
// @Description Create a new views
// @Tags views
// @Accept  json
// @Produce  json
// @Param account productId body string true "Product Id"
// @Success 201
// @Failure 400
// @Failure 403
// @Router /v1/views [post]
// @Param Authorization header string true "Bearer"
func (controller *ViewsController) CreateView(userId uint, productId uint) error {
	err := controller.viewService.CreateView(&entity.View{UserId: userId, ProductId: productId})
	if err != nil {
		return err
	}
	return nil
}
