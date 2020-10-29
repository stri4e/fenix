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

type FilterHandler struct {
	controller *controllers.FilterController
}

func NewFilterHandler(controller *controllers.FilterController) *FilterHandler {
	return &FilterHandler{controller: controller}
}

func (handler *FilterHandler) SaveFilter(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			subcategoryName := vars["subcategoryName"]
			utils.ThrowIfNil(subcategoryName, http.StatusBadRequest, "Request path is required.")
			var payload dto.FilterDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize FilterDto.")
			filter, err := handler.controller.Save(subcategoryName, &payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save FilterDto.")
			log.WithFields(log.Fields{"FilterTitle": payload.Title}).
				Debug("Enter: create new filter")
			ResponseSender(w, filter, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *FilterHandler) FindFilterById(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strFilterId := vars["filterId"]
			utils.ThrowIfNil(strFilterId, http.StatusBadRequest, "Request path is required.")
			filterId, err := strconv.ParseUint(strFilterId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize filter id.")
			payload, err := handler.controller.FindById(uint(filterId))
			utils.ThrowIfErr(err, http.StatusNotFound, "filter not found")
			log.Debug("Enter: find filter information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *FilterHandler) UpdateFilter(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.FilterDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize FilterDto.")
			err = handler.controller.Update(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update FilterDto.")
			log.WithFields(log.Fields{"FilterTitle": payload.Title}).
				Debug("Enter: update  filter")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
