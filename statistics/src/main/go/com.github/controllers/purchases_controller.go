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
// @Router /v1/purchases/{orderId} [get]
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
