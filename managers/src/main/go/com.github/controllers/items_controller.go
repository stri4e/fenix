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
