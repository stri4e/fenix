package handlers

import (
	"../controllers"
	"../dto"
	"../utils"
	"encoding/json"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"net/http"
)

type SubcategoryHandler struct {
	controller *controllers.SubcategoryController
}

func NewSubcategoryHandler(controller *controllers.SubcategoryController) *SubcategoryHandler {
	return &SubcategoryHandler{controller: controller}
}

func (handler *SubcategoryHandler) SaveSubcategory(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			categoryName := vars["categoryName"]
			utils.ThrowIfNil(categoryName, http.StatusBadRequest, "Request path is required.")
			var payload dto.SubcategoryDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize SubcategoryDto.")
			subcategory, err := handler.controller.Save(categoryName, &payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save SubcategoryDto.")
			log.WithFields(log.Fields{"SpecificationName": payload.Name}).
				Debug("Enter: create new subcategory")
			ResponseSender(w, subcategory, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *SubcategoryHandler) FindSubcategoryByName(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			subcategoryName := vars["subcategoryName"]
			utils.ThrowIfNil(subcategoryName, http.StatusBadRequest, "Request path is required.")
			payload, err := handler.controller.ReadByName(subcategoryName)
			utils.ThrowIfErr(err, http.StatusNotFound, "subcategory not found")
			log.Debug("Enter: find subcategory information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *SubcategoryHandler) UpdateSubcategory(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.SubcategoryDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize SubcategoryDto.")
			err = handler.controller.Update(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update SubcategoryDto.")
			log.WithFields(log.Fields{"SpecificationName": payload.Name}).
				Debug("Enter: update  subcategory")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
