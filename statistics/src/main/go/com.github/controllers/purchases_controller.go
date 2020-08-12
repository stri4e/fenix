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

// GetByAccountId godoc
// @Summary Get details of user purchases
// @Description Get details of user purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param accountId path integer true "Account ID"
// @Success 200 {object} dto.PurchaseDto
// @Router /v1/purchase/{accountId} [get]
func (controller *PurchasesController) GetByAccountId(accountId uint) ([]*dto.PurchaseDto, error) {
	purchases, err := controller.purchaseService.ReadByAccountId(accountId)
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
// @Router /v1/purchases [post]
func (controller *PurchasesController) CreatePurchase(payload *dto.PurchaseDto) (*dto.PurchaseDto, error) {
	purchase := utils.ToPurchase(payload)
	result, err := controller.purchaseService.CreatePurchase(purchase)
	if err != nil {
		return nil, err
	}
	return utils.FromPurchase(result), err
}
