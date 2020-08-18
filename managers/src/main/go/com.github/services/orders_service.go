package services

import (
	"../payload"
	"github.com/dghubble/sling"
)

type OrderService struct {
	eureka *EurekaService
}

func NewOrderService(eureka *EurekaService) *OrderService {
	return &OrderService{eureka: eureka}
}

type ids struct {
	Ids uint `url:"ids,omitempty"`
}

func (service *OrderService) GetOrders(orders []uint) (*[]payload.OrderDetail, error) {
	result := new([]payload.OrderDetail)
	client := sling.New().Get("http://127.0.0.1:8687/v1/fetch")
	for _, id := range orders {
		client.QueryStruct(&ids{Ids: id})
	}
	_, err := client.ReceiveSuccess(result)
	if err != nil {
		return nil, err
	}
	return result, nil
}
