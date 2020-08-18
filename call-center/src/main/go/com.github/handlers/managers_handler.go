package handlers

import (
	"../controllers"
)

type ManagersHandler struct {
	controller *controllers.ManagersController
}

func NewManagersHandler(controller *controllers.ManagersController) *ManagersHandler {
	return &ManagersHandler{controller: controller}
}
