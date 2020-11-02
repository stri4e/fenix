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

type ProductService struct {
	eureka *EurekaService
}

func NewProductService(eureka *EurekaService) *ProductService {
	return &ProductService{eureka: eureka}
}

func (service *ProductService) ReadProducts(products []uint) (*[]dto.ProductDto, error) {
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

func (service *ProductService) Create(subcategory string, product *dto.ProductDto) (*dto.ProductDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.ProductDto)
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Post(instance.HomePageUrl).
				Path("/v1/edit").
				Path(subcategory).
				BodyJSON(product).
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

func (service *ProductService) ReadById(productId uint) (*dto.ProductDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new(dto.ProductDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/fetch").
				Path(strconv.Itoa(int(productId)))
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

func (service *ProductService) ReadAllUnPublish() (*[]dto.ProductDto, error) {
	instances, err := service.getInstances()
	if err == nil {
		result := new([]dto.ProductDto)
		client := sling.New()
		for _, instance := range instances {
			client := client.Get(instance.HomePageUrl).
				Path("/v1/fetch/un-publish")
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

func (service *ProductService) UpdateStatusProduct(productId uint, status string) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Delete(instance.HomePageUrl).
				Path("/v1/edit/").
				Path(strconv.Itoa(int(productId))).
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

func (service *ProductService) UpdateProduct(product *dto.ProductDto) error {
	instances, err := service.getInstances()
	if err == nil {
		client := sling.New()
		for _, instance := range instances {
			resp, err := client.Put(instance.HomePageUrl).
				BodyJSON(product).
				Path("/v1/edit/").
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
	result := apps["PRODUCTS-SERVICE"]
	if result == nil {
		return nil, errors.New("instance not found")
	}
	return result.Instances, nil
}
