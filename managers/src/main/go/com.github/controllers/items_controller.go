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

type ItemsController struct {
	managerService *services.ManagersService
	itemService    *services.ItemService
	orderService   *services.OrderService
}

func NewItemsController(
	managerService *services.ManagersService, itemService *services.ItemService,
	orderService *services.OrderService) *ItemsController {
	return &ItemsController{
		managerService: managerService,
		itemService:    itemService,
		orderService:   orderService}
}

// SaveItem godoc
// @Summary Save a new items
// @Description Save a new items
// @Tags items
// @Accept  json
// @Produce  json
// @Param item body dto.ItemDto true "Save item"
// @Success 201
// @Router /v1 [post]
// @Param Authorization header string true "Bearer"
func (controller *ItemsController) SaveItem(mangerId uint, firstName string, lastName string, payload *dto.ItemDto) error {
	manager, err := controller.managerService.FirstOrCreateManager(
		&entity.Manager{ManagerId: mangerId, FirstName: firstName, LastName: lastName})
	if err != nil {
		return err
	}
	if manager != nil {
		item := utils.ToItem(mangerId, payload)
		item, err = controller.itemService.CreateItem(item)
		if err != nil {
			return err
		}
		err = controller.orderService.UpdateOrder(item.OrderId, Handling)
	}
	return err
}

// FindItems godoc
// @Summary Get a all orders by status
// @Description Get a all orders by status
// @Tags items
// @Accept  json
// @Produce  json
// @Param status path string true "Order Status"
// @Success 200 {object} dto.OrderDto
// @Router /v1/{status} [get]
func (controller *ItemsController) FindItems(managerId uint, status string) (*[]dto.OrderDto, error) {
	manager, err := controller.managerService.Find(managerId, status)
	if err != nil {
		return nil, err
	}
	var ordersIds []uint
	for _, i := range manager.Items {
		ordersIds = append(ordersIds, i.OrderId)
	}
	orders, err := controller.orderService.GetOrders(ordersIds)
	if err != nil {
		return nil, err
	}
	return orders, nil
}

// UpdateStatusItem godoc
// @Summary Update order by status
// @Description Update order by status
// @Tags items
// @Accept  json
// @Produce  json
// @Param orderId path integer true "Order ID"
// @Param status path string true "Order Status"
// @Success 200
// @Router /v1/{orderId}/{status} [put]
func (controller *ItemsController) UpdateStatusItem(orderId uint, status string) error {
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
	return controller.itemService.UpdateStatus(orderId, status)
}
