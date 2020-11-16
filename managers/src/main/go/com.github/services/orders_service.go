package services

import (
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"managers/src/main/go/com.github/dto"
	"managers/src/main/go/com.github/utils"
	"net/http"
	"strconv"
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

func (service *OrderService) GetOrdersByStatus(status string) (*[]dto.OrderDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new([]dto.OrderDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/fetch").
				Path(status)
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

func (service *OrderService) GetBindingOrders(orderId uint) (*[]dto.OrderDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new([]dto.OrderDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/fetch/binding").
				Path(strconv.Itoa(int(orderId)))
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
				Path(strconv.Itoa(int(orderId))).
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
	result := apps["ORDERS"]
	if result == nil {
		return nil, errors.New("instance not found")
	}
	return result.Instances, nil
}
