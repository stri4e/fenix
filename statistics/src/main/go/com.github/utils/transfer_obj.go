package utils

import (
	"../dto"
	"../entity"
)

// ===========================================================
// ================= CONVERT FROM ENTITY =====================
// ===========================================================

func FromViews(data []*entity.View) []uint {
	var productsIds []uint
	for _, view := range data {
		productsIds = append(productsIds, view.ProductId)
	}
	return productsIds
}

func FromLogin(data *entity.Login) *dto.LoginDto {
	return &dto.LoginDto{
		Id:        data.ID,
		CreatedAt: data.CreatedAt,
		Device:    data.Device,
		Location:  data.Location,
	}
}

func FromLoginArray(data []*entity.Login) []*dto.LoginDto {
	var logins []*dto.LoginDto
	for _, login := range data {
		logins = append(logins, FromLogin(login))
	}
	return logins
}

// ===========================================================
// ================= CONVERT TO ENTITY =======================
// ===========================================================

func ToLogin(data *dto.LoginDto) *entity.Login {
	return &entity.Login{
		UserId:   data.UserId,
		Device:   data.Device,
		Location: data.Location,
	}
}
