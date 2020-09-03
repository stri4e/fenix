package controllers

import (
	"../dto"
	"../entity"
	"../services"
	"../utils"
)

type NewOrdersController struct {
	orderService    *services.OrderService
	newOrderService *services.NewOrdersService
}

func NewNewOrdersController(orderService *services.OrderService, newOrderService *services.NewOrdersService) *NewOrdersController {
	return &NewOrdersController{orderService: orderService, newOrderService: newOrderService}
}

// FindBetweenTime godoc
// @Summary Get details of user orders
// @Description Get details of user orders
// @Tags views
// @Accept  json
// @Produce  json
// @Param start query string false "name search by start"
// @Param end query string false "name search by end"
// @Success 200 {object} dto.OrderDto
// @Router /v1/orders/fetch [get]
func (controller *NewOrdersController) FindBetweenTime(start string, end string) (*[]dto.OrderDto, error) {
	newOrders, err := controller.newOrderService.ReadBetweenTime(start, end)
	if err != nil {
		return nil, err
	}
	orderIds := utils.FromOrders(newOrders)
	orders, err := controller.orderService.GetOrders(orderIds)
	if err != nil {
		return nil, err
	}
	return orders, nil
}

// CreateView godoc
// @Summary Create a new views
// @Description Create a new views
// @Tags views
// @Accept  json
// @Produce  json
// @Param view body string true "Create view"
// @Success 201
// @Router /v1/orders [post]
// @Param Authorization header string true "Bearer"
func (controller *NewOrdersController) CreateOrder(orderId uint) error {
	err := controller.newOrderService.Create(&entity.NewOrder{OrderId: orderId})
	if err != nil {
		return err
	}
	return nil
}
