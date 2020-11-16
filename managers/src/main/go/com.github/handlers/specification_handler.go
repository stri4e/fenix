package handlers

import (
	"encoding/json"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"managers/src/main/go/com.github/controllers"
	"managers/src/main/go/com.github/dto"
	"managers/src/main/go/com.github/utils"
	"net/http"
	"strconv"
)

type SpecificationHandler struct {
	controller *controllers.SpecificationController
}

func NewSpecificationHandler(controller *controllers.SpecificationController) *SpecificationHandler {
	return &SpecificationHandler{controller: controller}
}

func (handler *SpecificationHandler) FindSpecById(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strSpecId := vars["specificationId"]
			utils.ThrowIfNil(strSpecId, http.StatusBadRequest, "Request path is required.")
			specId, err := strconv.ParseUint(strSpecId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize specification id.")
			payload, err := handler.controller.FindSpecById(uint(specId))
			utils.ThrowIfErr(err, http.StatusNotFound, "specification not found")
			log.Debug("Enter: find specification information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *SpecificationHandler) SaveSpecification(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			str := vars["productId"]
			utils.ThrowIfNil(str, http.StatusBadRequest, "Request path is required.")
			productId, err := strconv.ParseUint(str, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize specification id.")
			var payload dto.SpecificationDto
			err = json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize SpecificationDto.")
			specification, err := handler.controller.SaveSpecification(uint(productId), &payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save SpecificationDto.")
			log.WithFields(log.Fields{"SpecificationName": payload.Name}).
				Debug("Enter: create new specification")
			ResponseSender(w, specification, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *SpecificationHandler) UpdateSpecification(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.SpecificationDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize SpecificationDto.")
			err = handler.controller.UpdateSpecification(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update SpecificationDto.")
			log.WithFields(log.Fields{"SpecificationName": payload.Name}).
				Debug("Enter: update  specification")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
