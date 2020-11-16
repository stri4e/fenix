package services

import (
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"managers/src/main/go/com.github/dto"
	"net/http"
	"strconv"
)

type FilterService struct {
	eureka *EurekaService
}

func NewFilterService(eureka *EurekaService) *FilterService {
	return &FilterService{eureka: eureka}
}

func (service *FilterService) Create(subcategoryName string, subcategory *dto.FilterDto) (*dto.FilterDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.FilterDto)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/filters/edit").
				Path(subcategoryName).
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

func (service *FilterService) ReadById(id uint) (*dto.FilterDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.FilterDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/filters/fetch").
				Path(strconv.Itoa(int(id)))
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

func (service *FilterService) Update(filter *dto.FilterDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(filter).
				Path("/v1/filters/edit").
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

func (service *FilterService) getInstances() ([]*fargo.Instance, error) {
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
