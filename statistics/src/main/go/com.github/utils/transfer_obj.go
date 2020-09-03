package utils

import (
	"../dto"
	"../entity"
	"../payload"
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

func FromProduct(data *payload.Product) *dto.ProductDto {
	var specifications []*dto.SpecificationDto
	for _, s := range data.Specifications {
		specifications = append(specifications, FromSpecification(s))
	}
	var comments []*dto.CommentDto
	for _, c := range data.Comments {
		comments = append(comments, FromComment(c))
	}
	return &dto.ProductDto{
		Id:             data.Id,
		Name:           data.Name,
		Price:          data.Price,
		Quantity:       data.Quantity,
		Description:    data.Description,
		PreviewImage:   data.PreviewImage,
		Images:         data.Images,
		Specifications: specifications,
		Comments:       comments,
		Category:       data.Category.Name,
	}
}

func FromSpecification(data *payload.Specification) *dto.SpecificationDto {
	return &dto.SpecificationDto{
		Id:          data.Id,
		Name:        data.Name,
		Description: data.Description,
	}
}

func FromComment(data *payload.Comment) *dto.CommentDto {
	return &dto.CommentDto{
		Id:          data.Id,
		Name:        data.Name,
		Description: data.Description,
	}
}

func FromProducts(data *[]payload.Product) []*dto.ProductDto {
	var products []*dto.ProductDto
	for _, p := range *data {
		products = append(products, FromProduct(&p))
	}
	return nil
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
