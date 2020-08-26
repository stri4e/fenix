package controllers

import (
	"../dto"
	"../services"
	"../utils"
)

type PurchasesController struct {
	purchaseService *services.PurchaseService
}

func NewPurchasesController(purchaseService *services.PurchaseService) *PurchasesController {
	return &PurchasesController{purchaseService: purchaseService}
}

// FindByUserId godoc
// @Summary Get details of user purchases
// @Description Get details of user purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param userId path integer true "User ID"
// @Success 200 {object} dto.PurchaseDto
// @Router /v1/purchase/fetch/{userId} [get]
func (controller *PurchasesController) FindByUserId(userId uint) ([]*dto.PurchaseDto, error) {
	purchases, err := controller.purchaseService.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	return utils.FromPurchaseArray(purchases), nil
}

// FindBetweenTime godoc
// @Summary Get details of user purchases
// @Description Get details of user purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param start query string false "name search by start"
// @Param end query string false "name search by end"
// @Success 200 {object} dto.PurchaseDto
// @Router /v1/purchases/fetch [get]
func (controller *PurchasesController) FindBetweenTime(start string, end string) ([]*dto.PurchaseDto, error) {
	purchases, err := controller.purchaseService.ReadBetweenTime(start, end)
	if err != nil {
		return nil, err
	}
	return utils.FromPurchaseArray(purchases), nil
}

// FindByUserId godoc
// @Summary Get details of user purchases
// @Description Get details of user purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param Authorization header string true "Bearer"
// @Success 200 {object} dto.PurchaseDto
// @Router /v1/purchases [get]
func (controller *PurchasesController) FindPurchases(userId uint) ([]*dto.PurchaseDto, error) {
	purchases, err := controller.purchaseService.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	return utils.FromPurchaseArray(purchases), nil
}

// FindPurchasesByOrderId godoc
// @Summary Get details of user purchases
// @Description Get details of user purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param orderId path integer true "Order ID"
// @Success 200 {object} dto.PurchaseDto
// @Router /v1/purchases/fetch/{orderId} [get]
func (controller *PurchasesController) FindPurchasesByOrderId(orderId uint) ([]*dto.PurchaseDto, error) {
	userId, err := controller.purchaseService.ReadUserId(orderId)
	if err != nil {
		return nil, err
	}
	purchases, err := controller.purchaseService.ReadByUserId(userId)
	if err != nil {
		return nil, err
	}
	return utils.FromPurchaseArray(purchases), nil
}

// CreatePurchase godoc
// @Summary Create a new purchases
// @Description Create a new purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param purchase body dto.PurchaseDto true "Create purchase"
// @Success 200 {object} dto.PurchaseDto
// @Router /v1/purchases/edit [post]
func (controller *PurchasesController) CreatePurchase(payload *dto.PurchaseDto) (*dto.PurchaseDto, error) {
	purchase := utils.ToPurchase(payload)
	result, err := controller.purchaseService.CreatePurchase(purchase)
	if err != nil {
		return nil, err
	}
	return utils.FromPurchase(result), err
}

// CreateManagerPurchase godoc
// @Summary Update purchase by status
// @Description Update purchase by status
// @Tags purchases
// @Param orderId path integer true "Order ID"
// @Param status path string true "Order Status"
// @Param purchase body dto.ManagerDto true "Create manager purchase"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/purchases/manager/edit/{orderId}/{status} [post]
func (controller *PurchasesController) CreateManagerPurchase(orderId uint, status string, payload *dto.ManagerDto) error {
	manager := utils.ToManager(payload)
	return controller.purchaseService.CreateManagerPurchase(orderId, status, manager)
}

// UpdateStatusPurchase godoc
// @Summary Update purchase by status
// @Description Update purchase by status
// @Tags purchases
// @Param orderId path integer true "Order ID"
// @Param status path string true "Order Status"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/purchases/edit/{orderId}/{status} [put]
func (controller *PurchasesController) UpdateStatusPurchase(orderId uint, status string) error {
	return controller.purchaseService.UpdateStatusPurchase(orderId, status)
}
