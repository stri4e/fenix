package utils

import (
	"../dto"
	"../entity"
)

func ToItem(managerId uint, data *dto.ItemDto) *entity.Item {
	return &entity.Item{ManagerId: managerId, OrderId: data.OrderId, Status: data.Status}
}

func FromManager(manager *entity.Manager) *dto.ManagerDto {
	return &dto.ManagerDto{
		ManagerId: manager.ManagerId,
		FirstName: manager.FirstName,
		LastName: manager.LastName,
	}
}
