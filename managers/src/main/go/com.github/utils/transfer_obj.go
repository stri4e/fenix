package utils

import (
	"../dto"
	"../entity"
)

func ToPurchase(managerId uint, data *dto.PurchaseDto) *entity.Purchase {
	return &entity.Purchase{ManagerId: managerId, OrderId: data.OrderId, Status: data.Status}
}

func FromManager(manager *entity.Manager) *dto.ManagerDto {
	return &dto.ManagerDto{
		FirstName: manager.FirstName,
		LastName:  manager.LastName,
	}
}
