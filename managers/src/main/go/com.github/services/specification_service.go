package services

import (
	"../dto"
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"net/http"
	"strconv"
)

type SpecificationService struct {
	eureka *EurekaService
}

func NewSpecificationService(eureka *EurekaService) *SpecificationService {
	return &SpecificationService{eureka: eureka}
}

func (service *SpecificationService) Create(productId uint, specification *dto.SpecificationDto) (*dto.SpecificationDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.SpecificationDto)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/specification/edit").
				Path(strconv.Itoa(int(productId))).
				BodyJSON(specification).
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

func (service *SpecificationService) ReadById(specId uint) (*dto.SpecificationDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.SpecificationDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/specification/fetch").
				Path(strconv.Itoa(int(specId)))
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

func (service *SpecificationService) Update(specification *dto.SpecificationDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(specification).
				Path("/v1/specification/edit").
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

func (service *SpecificationService) getInstances() ([]*fargo.Instance, error) {
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
