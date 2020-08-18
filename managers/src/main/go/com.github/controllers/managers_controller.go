package controllers

import (
	"../dto"
	"../entity"
	"../services"
	"../utils"
)

type ItemsController struct {
	managerService *services.ManagersService
	itemService    *services.ItemService
}

func NewItemsController(managerService *services.ManagersService, itemService *services.ItemService) *ItemsController {
	return &ItemsController{managerService: managerService, itemService: itemService}
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
	}
	return err
}
