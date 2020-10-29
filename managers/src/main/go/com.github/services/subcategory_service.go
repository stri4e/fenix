package services

import (
	"../dto"
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"net/http"
)

type SubcategoryService struct {
	eureka *EurekaService
}

func NewSubcategoryService(eureka *EurekaService) *SubcategoryService {
	return &SubcategoryService{eureka: eureka}
}

func (service *SubcategoryService) Create(categoryName string, subcategory *dto.SubcategoryDto) (*dto.SubcategoryDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.SubcategoryDto)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/subcategories/edit").
				Path(categoryName).
				BodyJSON(subcategory).
				ReceiveSuccess(result)
			if err != nil {
				return nil, err
			}
			if resp.StatusCode == http.StatusCreated {
				return result, nil
			}
		}
	}
	return nil, err
}

func (service *SubcategoryService) ReadByName(name string) (*dto.SubcategoryDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.SubcategoryDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/subcategories/fetch").
				Path(name)
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

func (service *SubcategoryService) Update(subcategory *dto.SubcategoryDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(subcategory).
				Path("/v1/subcategories/edit").
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

func (service *SubcategoryService) getInstances() ([]*fargo.Instance, error) {
	apps, err := service.eureka.connection.GetApps()
	if err != nil {
		return nil, err
	}
	result := apps["PRODUCTS-SERVICE"]
	if result == nil {
		return nil, errors.New("instance not found")
	}
	return result.Instances, nil
}
