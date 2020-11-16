package controllers

import (
	"managers/src/main/go/com.github/dto"
	"managers/src/main/go/com.github/services"
	"managers/src/main/go/com.github/utils"
)

type ManagersController struct {
	purchaseService *services.PurchaseService
	managerService  *services.ManagersService
}

func NewManagersController(
	purchaseService *services.PurchaseService,
	managerService *services.ManagersService) *ManagersController {
	return &ManagersController{
		purchaseService: purchaseService,
		managerService:  managerService,
	}
}

// FindManagerByOrderId godoc
// @Summary Get manager by order id
// @Description Get manager by order id
// @Tags manager
// @Accept  json
// @Produce  json
// @Param status path int true "Order ID"
// @Success 200 {object} dto.ManagerDto
// @Failure 400
// @Failure 403
// @Router /v1/fetch/{orderId} [get]
func (controller *ManagersController) FindManagerByOrderId(orderId uint) (*dto.ManagerDto, error) {
	order, err := controller.purchaseService.FindPurchaseByOrderId(orderId)
	if err != nil {
		return nil, err
	}
	manager, err := controller.managerService.ReadByManagerID(order.ManagerId)
	if err != nil {
		return nil, err
	}
	return utils.FromManager(manager), nil
}
