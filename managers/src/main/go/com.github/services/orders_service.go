package services

import (
	"../dto"
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

func (service *OrderService) GetOrders(orders []uint) (*[]dto.OrderDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new([]dto.OrderDto)
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

func (service *OrderService) UpdateOrder(orderId uint, status string) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				Path("/v1/edit/").
				Path(string(orderId)).
				Path("/").
				Path(status).
				Receive(nil, nil)
			if err != nil {
				return err
			}
			if resp.StatusCode == http.StatusOK {
				return nil
			}
		}
	}
	return err
}

func (service *OrderService) getInstances() ([]*fargo.Instance, error) {
	apps, err := service.eureka.connection.GetApps()
	if err != nil {
		return nil, err
	}
	return apps["ORDERS-SERVICE"].Instances, nil
}
