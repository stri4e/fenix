package utils

import (
	"../dto"
	"../entity"
	"../payload"
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

func FromCategory(data *payload.Category) *dto.CategoryDto {
	return &dto.CategoryDto{Id: data.Id, Name: data.Name}
}

func FromProduct(data *payload.Product) *dto.ProductDto {
	return &dto.ProductDto{
		Id:           data.Id,
		Name:         data.Name,
		Price:        data.Price,
		Quantity:     data.Quantity,
		Description:  data.Description,
		PreviewImage: data.PreviewImage,
		Images:       data.Images,
	}
}

func ToCategory(data *dto.CategoryDto) *payload.Category {
	return &payload.Category{Id: data.Id, Name: data.Name}
}

func ToProduct(data *dto.ProductDto) *payload.Product {
	return &payload.Product{
		Id:           data.Id,
		Name:         data.Name,
		Price:        data.Price,
		Quantity:     data.Quantity,
		Description:  data.Description,
		PreviewImage: data.PreviewImage,
		Images:       data.Images,
	}
}
