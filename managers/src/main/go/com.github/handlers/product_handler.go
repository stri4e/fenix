package handlers

import (
	"../controllers"
	"../dto"
	"../utils"
	"encoding/json"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"net/http"
	"strconv"
)

type ProductHandler struct {
	controller *controllers.ProductController
}

func NewProductHandler(controller *controllers.ProductController) *ProductHandler {
	return &ProductHandler{controller: controller}
}

func (handler *ProductHandler) FindProductById(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strProductId := vars["productId"]
			utils.ThrowIfNil(strProductId, http.StatusBadRequest, "Request path is required.")
			productId, err := strconv.ParseUint(strProductId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize product id.")
			payload, err := handler.controller.FindById(uint(productId))
			utils.ThrowIfErr(err, http.StatusNotFound, "product not found")
			log.Debug("Enter: find product information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ProductHandler) FindAllProduct(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			payload, err := handler.controller.FindAllUnPublish()
			utils.ThrowIfErr(err, http.StatusNotFound, "un publish products not found")
			log.Debug("Enter: find un publish products information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ProductHandler) SaveProduct(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			categoryName := vars["categoryName"]
			var payload dto.ProductDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize ProductDto.")
			product, err := handler.controller.SaveProduct(categoryName, &payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save ProductDto.")
			log.WithFields(log.Fields{"ProductName": payload.Name}).
				Debug("Enter: create new product")
			ResponseSender(w, product, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ProductHandler) UpdateProduct(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.ProductDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize ProductDto.")
			err = handler.controller.UpdateProduct(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update ProductDto.")
			log.WithFields(log.Fields{"ProductName": payload.Name}).
				Debug("Enter: update  product")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ProductHandler) UpdateStatusProduct(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			status := vars["status"]
			strProductId := vars["productId"]
			utils.ThrowIfNil(status, http.StatusBadRequest, "Request path is required.")
			utils.ThrowIfNil(strProductId, http.StatusBadRequest, "Request path is required.")
			productId, err := strconv.ParseUint(strProductId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize product id.")
			err = handler.controller.UpdateStatusProduct(uint(productId), status)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update ProductDto.")
			log.Debug("Enter: update status product")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
