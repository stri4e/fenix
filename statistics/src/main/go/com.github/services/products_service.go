package services

import (
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"net/http"
	"statistics/src/main/go/com.github/dto"
	"statistics/src/main/go/com.github/utils"
)

type ProductService struct {
	eureka *EurekaService
}

func NewProductService(eureka *EurekaService) *ProductService {
	return &ProductService{eureka: eureka}
}

func (service *ProductService) GetProducts(products []uint) (*[]dto.ProductDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new([]dto.ProductDto)
		client := sling.New()
		for _, id := range products {
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

func (service *ProductService) UpdatePercentsBought(percentsBought *[]dto.BoughtCountDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(percentsBought).
				Path("/v1/edit/bought/count").
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

func (service *ProductService) getInstances() ([]*fargo.Instance, error) {
	apps, err := service.eureka.connection.GetApps()
	if err != nil {
		return nil, err
	}
	result := apps["PRODUCTS"]
	if result == nil {
		return nil, errors.New("instance not found")
	}
	return result.Instances, nil
}
