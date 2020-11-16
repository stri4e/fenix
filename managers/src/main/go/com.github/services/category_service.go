package services

import (
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"managers/src/main/go/com.github/dto"
	"net/http"
)

type CategoryService struct {
	eureka *EurekaService
}

func NewCategoryService(eureka *EurekaService) *CategoryService {
	return &CategoryService{eureka: eureka}
}

func (service *CategoryService) ReadByName(categoryName string) (*dto.CategoryDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.CategoryDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/categories/fetch").
				Path(categoryName)
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

func (service *CategoryService) CreateCategory(category *dto.CategoryDto) (*dto.CategoryDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.CategoryDto)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/categories/edit").
				BodyJSON(category).
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

func (service *CategoryService) UpdateCategory(category *dto.CategoryDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(category).
				Path("/v1/categories/edit").
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

func (service *CategoryService) getInstances() ([]*fargo.Instance, error) {
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
