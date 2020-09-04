package controllers

import (
	"../dto"
	"../services"
)

type UserOrdersController struct {
	orderService *services.OrderService
}

func NewUserOrdersController(orderService *services.OrderService) *UserOrdersController {
	return &UserOrdersController{orderService: orderService}
}

// FindBindingOrders godoc
// @Summary Get binding orders by order id
// @Description Get binding orders by order id
// @Tags user-orders
// @Accept  json
// @Produce  json
// @Param status path int true "Order ID"
// @Success 200 {object} dto.OrderDto
// @Failure 400
// @Failure 403
// @Router /v1/binding/{orderId} [get]
func (controller *UserOrdersController) FindBindingOrders(orderId uint) (*[]dto.OrderDto, error) {
	orders, err := controller.orderService.GetBindingOrders(orderId)
	if err != nil {
		return nil, err
	}
	return orders, nil
}
