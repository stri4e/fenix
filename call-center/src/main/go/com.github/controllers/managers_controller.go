package controllers

import (
	"../services"
)

type ManagersController struct {
	service *services.ManagersService
}

func NewManagersController(service *services.ManagersService) *ManagersController {
	return &ManagersController{service: service}
}
