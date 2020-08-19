package services

import (
	"../payload"
	"../utils"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"net/http"
)

type OrderService struct {
	eureka *EurekaService
}

func NewOrderService(eureka *EurekaService) *OrderService {
	return &OrderService{eureka: eureka}
}

func (service *OrderService) GetOrders(orders []uint) (*[]payload.Order, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new([]payload.Order)
		client := sling.New()
		for _, id := range orders {
			client.QueryStruct(&utils.Ids{Ids: id})
		}
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).Path("/v1/fetch")
			resp, err := client.ReceiveSuccess(result)
			if err != nil {
				return nil, err
			}
			if resp.StatusCode == http.StatusOK {
				return result, nil
			}
		}
	}
	return nil, err
}

func (service *OrderService) getInstances() ([]*fargo.Instance, error) {
	apps, err := service.eureka.connection.GetApps()
	if err != nil {
		return nil, err
	}
	return apps["ORDERS-SERVICE"].Instances, nil
}
