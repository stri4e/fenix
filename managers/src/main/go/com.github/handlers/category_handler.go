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

type CategoryHandler struct {
	controller *controllers.CategoryController
}

func NewCategoryHandler(controller *controllers.CategoryController) *CategoryHandler {
	return &CategoryHandler{controller: controller}
}

func (handler *CategoryHandler) FindByCategoryName(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			categoryName := vars["categoryName"]
			utils.ThrowIfNil(categoryName, http.StatusBadRequest, "Request path is required.")
			payload, err := handler.controller.FindByCategoryName(categoryName)
			utils.ThrowIfErr(err, http.StatusNotFound, "category not found")
			log.Debug("Enter: find category information")
			ResponseSender(w, payload, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CategoryHandler) SaveCategory(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.CategoryDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize CategoryDto.")
			login, err := handler.controller.SaveCategory(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't save CategoryDto.")
			log.WithFields(log.Fields{"CategoryName": payload.Name}).
				Debug("Enter: create new  category")
			ResponseSender(w, login, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *CategoryHandler) UpdateCategory(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var payload dto.CategoryDto
			err := json.NewDecoder(r.Body).Decode(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize CategoryDto.")
			login, err := handler.controller.SaveCategory(&payload)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update CategoryDto.")
			log.WithFields(log.Fields{"CategoryName": payload.Name}).
				Debug("Enter: update  category")
			ResponseSender(w, login, http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
