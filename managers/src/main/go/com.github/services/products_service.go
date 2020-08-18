package services

import (
	"../payload"
	"github.com/dghubble/sling"
)

type ProductService struct {
	eureka *EurekaService
}

func NewProductService(eureka *EurekaService) *ProductService {
	return &ProductService{eureka: eureka}
}

func (service *ProductService) GetProducts(products []uint) (*[]payload.Product, error) {
	apps, err := service.eureka.connection.GetApps()
	instance := apps["PRODUCTS-SERVICE"].Instances[0]
	result := new([]payload.Product)
	client := sling.New().Get(instance.HomePageUrl + "/v1/fetch")
	for _, id := range products {
		client.QueryStruct(&ids{Ids: id})
	}
	_, err = client.ReceiveSuccess(result)
	if err != nil {
		return nil, err
	}
	return result, nil
}
