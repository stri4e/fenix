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

type CriteriaHandler struct {
	controller *controllers.CriteriaController
}

func NewCriteriaHandler(controller *controllers.CriteriaController) *CriteriaHandler {
	return &CriteriaHandler{controller: controller}
}

func (handler *CriteriaHandler) SaveToFilter(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strFilterId := vars["filterId"]
			utils.ThrowIfNil(strFilterId, http.StatusBadRequest, "Request path is required.")
			filterId, err := strconv.ParseUint(strFilterId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize filter id.")
			var payload dto.CriteriaDto
			err = json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize CriteriaDto.")
			filter, err := handler.controller.SaveToFilter(uint(filterId), &payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save CriteriaDto.")
			log.WithFields(log.Fields{"CriteriaTitle": payload.Value}).
				Debug("Enter: create new criteria")
			ResponseSender(w, filter, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CriteriaHandler) SaveToProducts(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strProductId := vars["productId"]
			utils.ThrowIfNil(strProductId, http.StatusBadRequest, "Request path is required.")
			productId, err := strconv.ParseUint(strProductId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize filter id.")
			var payload []uint
			err = json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize FilterDto.")
			err = handler.controller.SaveToProducts(uint(productId), payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save FilterDto.")
			ResponseSender(w, "", http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CriteriaHandler) FindCriteriaById(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strCriteriaId := vars["filterId"]
			utils.ThrowIfNil(strCriteriaId, http.StatusBadRequest, "Request path is required.")
			criteriaId, err := strconv.ParseUint(strCriteriaId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize filter id.")
			payload, err := handler.controller.FindById(uint(criteriaId))
			utils.ThrowIfErr(err, http.StatusNotFound, "filter not found")
			log.Debug("Enter: find filter information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CriteriaHandler) UpdateCriteria(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.CriteriaDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize CriteriaDto.")
			err = handler.controller.Update(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update CriteriaDto.")
			log.WithFields(log.Fields{"Criteria Value": payload.Value}).
				Debug("Enter: update  criteria")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CriteriaHandler) UpdateInProduct(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strProductId := vars["productId"]
			strCriteriaId := vars["criteriaId"]
			utils.ThrowIfNil(strProductId, http.StatusBadRequest, "Request path is required.")
			productId, err := strconv.ParseUint(strProductId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize filter id.")
			utils.ThrowIfNil(strCriteriaId, http.StatusBadRequest, "Request path is required.")
			criteriaId, err := strconv.ParseUint(strProductId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize filter id.")
			err = handler.controller.UpdateInProduct(uint(productId), uint(criteriaId))
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update Criteria.")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CriteriaHandler) UpdateInFilters(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strFilterId := vars["filterId"]
			strCriteriaId := vars["criteriaId"]
			utils.ThrowIfNil(strFilterId, http.StatusBadRequest, "Request path is required.")
			filterId, err := strconv.ParseUint(strFilterId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize product id.")
			utils.ThrowIfNil(strCriteriaId, http.StatusBadRequest, "Request path is required.")
			criteriaId, err := strconv.ParseUint(strCriteriaId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize criteria id.")
			err = handler.controller.UpdateInFilters(uint(filterId), uint(criteriaId))
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update Criteria.")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
