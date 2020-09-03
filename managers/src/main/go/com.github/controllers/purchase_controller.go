package controllers

import (
	"../dto"
	"../entity"
	"../services"
	"../utils"
)

const (
	Handling = "handling"
	Close    = "close"
	Open     = "open"
)

type PurchasesController struct {
	managerService  *services.ManagersService
	purchaseService *services.PurchaseService
	orderService    *services.OrderService
}

func NewPurchasesController(
	managerService *services.ManagersService,
	purchaseService *services.PurchaseService,
	orderService *services.OrderService) *PurchasesController {
	return &PurchasesController{
		managerService:  managerService,
		purchaseService: purchaseService,
		orderService:    orderService,
	}
}

// SavePurchase godoc
// @Summary Save a new purchases
// @Description Save a new purchases
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param purchase body dto.PurchaseDto true "Save purchase"
// @Success 201
// @Failure 400
// @Failure 403
// @Router /v1 [post]
// @Param Authorization header string true "Bearer"
func (controller *PurchasesController) SavePurchases(mangerId uint, firstName string, lastName string, payload *dto.PurchaseDto) error {
	manager, err := controller.managerService.FirstOrCreateManager(
		&entity.Manager{ManagerId: mangerId, FirstName: firstName, LastName: lastName})
	if err != nil {
		return err
	}
	err = controller.orderService.UpdateOrder(payload.OrderId, Handling)
	if err != nil {
		return err
	}
	if manager != nil {
		purchase := utils.ToPurchase(mangerId, payload)
		purchase, err = controller.purchaseService.CreatePurchase(purchase)
		if err != nil {
			return err
		}
	}
	return err
}

// FindItems godoc
// @Summary Get a all orders by status
// @Description Get a all orders by status
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param status path string true "Order Status"
// @Success 200 {object} dto.OrderDto
// @Failure 400
// @Failure 403
// @Router /v1/{status} [get]
func (controller *PurchasesController) FindItems(managerId uint, status string) (*[]dto.OrderDto, error) {
	manager, err := controller.managerService.Find(managerId, status)
	if err != nil {
		return nil, err
	}
	var ordersIds []uint
	for _, i := range manager.Purchases {
		ordersIds = append(ordersIds, i.OrderId)
	}
	orders, err := controller.orderService.GetOrders(ordersIds)
	if err != nil {
		return nil, err
	}
	return orders, nil
}

// FindAllPurchases godoc
// @Summary Get a all orders by status
// @Description Get a all orders by status
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param status path string true "Order Status"
// @Success 200 {object} dto.OrderDto
// @Failure 400
// @Failure 403
// @Router /v1/all/{status} [get]
func (controller *PurchasesController) FindAllPurchases(status string) (*[]dto.OrderDto, error) {
	orders, err := controller.orderService.GetOrdersByStatus(status)
	if err != nil {
		return nil, err
	}
	return orders, nil
}

// UpdateStatusPurchase godoc
// @Summary Update order by status
// @Description Update order by status
// @Tags purchases
// @Accept  json
// @Produce  json
// @Param orderId path integer true "Order ID"
// @Param status path string true "Order Status"
// @Success 200
// @Failure 403
// @Failure 404
// @Router /v1/{orderId}/{status} [put]
func (controller *PurchasesController) UpdateStatusPurchase(orderId uint, status string) error {
	var err error
	switch status {
	case Open:
		err = controller.orderService.UpdateOrder(orderId, Handling)
	case Close:
		err = controller.orderService.UpdateOrder(orderId, Close)
	}
	if err != nil {
		return err
	}
	return controller.purchaseService.UpdateStatus(orderId, status)
}
