package services

import (
	"../dto"
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"net/http"
)

type PurchaseService struct {
	eureka *EurekaService
}

func NewPurchaseService(eureka *EurekaService) *PurchaseService {
	return &PurchaseService{eureka: eureka}
}

func (service *PurchaseService) UpdatePurchase(orderId uint, status string, manager *dto.ManagerDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New().Path("/v1/purchases/edit").
			Path(string(orderId)).
			Path(status).
			BodyJSON(manager)
		for _, instance := range instances {
			client := client.Put(instance.HomePageUrl)
			resp, err := client.ReceiveSuccess(nil)
			if err != nil {
				return err
			}
			if resp.StatusCode == http.StatusOK {
				return nil
			}
		}
	}
	return nil
}

func (service *PurchaseService) getInstances() ([]*fargo.Instance, error) {
	apps, err := service.eureka.connection.GetApps()
	if err != nil {
		return nil, err
	}
	result := apps["STATISTICS"]
	if result == nil {
		return nil, errors.New("instance not found")
	}
	return result.Instances, nil
}