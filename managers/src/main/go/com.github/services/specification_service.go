package services

import (
	"../payload"
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

func (service *SpecificationService) Create(specification *payload.Specification) (*payload.Specification, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(payload.Specification)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/specification/edit").
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

func (service *SpecificationService) ReadById(specId uint) (*payload.Specification, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(payload.Specification)
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

func (service *SpecificationService) Update(specification *payload.Specification) error {
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
