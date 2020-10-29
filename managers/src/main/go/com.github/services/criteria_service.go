package services

import (
	"../dto"
	"errors"
	"github.com/dghubble/sling"
	"github.com/hudl/fargo"
	"net/http"
	"strconv"
)

type CriteriaService struct {
	eureka *EurekaService
}

func NewCriteriaService(eureka *EurekaService) *CriteriaService {
	return &CriteriaService{eureka: eureka}
}

func (service *CriteriaService) CreateToFilter(filterId uint, criteria *dto.CriteriaDto) (*dto.CriteriaDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.CriteriaDto)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/criteria/edit/to/filters").
				Path(strconv.Itoa(int(filterId))).
				BodyJSON(criteria).
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

func (service *CriteriaService) CreateToProducts(productId uint, criteria []uint) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/criteria/edit/to/products").
				Path(strconv.Itoa(int(productId))).
				BodyJSON(criteria).
				Receive(nil, nil)
			if err != nil {
				return err
			}
			if resp.StatusCode == http.StatusCreated {
				return nil
			}
		}
	}
	return err
}

func (service *CriteriaService) ReadById(id uint) (*dto.CriteriaDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.CriteriaDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/criteria/fetch").
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

func (service *CriteriaService) Update(criteria *dto.CriteriaDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(criteria).
				Path("/v1/criteria/edit").
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

func (service *CriteriaService) UpdateInProduct(productId uint, criteriaId uint) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				Path("/v1/criteria/edit/in/products").
				Path(strconv.Itoa(int(productId))).
				Path(strconv.Itoa(int(criteriaId))).
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

func (service *CriteriaService) UpdateInFilters(filterId uint, criteriaId uint) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				Path("/v1/criteria/edit/in/filters").
				Path(strconv.Itoa(int(filterId))).
				Path(strconv.Itoa(int(criteriaId))).
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

func (service *CriteriaService) getInstances() ([]*fargo.Instance, error) {
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
