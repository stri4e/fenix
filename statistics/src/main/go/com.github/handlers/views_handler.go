package handlers

import (
	"../controllers"
	"../utils"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"strconv"
)

type ViewsHandler struct {
	controller *controllers.ViewsController
}

func NewViewsHandler(controller *controllers.ViewsController) *ViewsHandler {
	return &ViewsHandler{controller: controller}
}

func (handler *ViewsHandler) FindByUserId(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			userId := vars["userId"]
			utils.ThrowIfNil(userId, http.StatusBadRequest, "Request path is required.")
			products, err := handler.controller.FindByUserId(userId)
			utils.ThrowIfErr(err, http.StatusNotFound, "Views not found.")
			ResponseSender(w, products, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ViewsHandler) FindBetweenTime(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			start := r.FormValue("start")
			end := r.FormValue("end")
			utils.ThrowIfNil(start, http.StatusBadRequest, "Request path is required.")
			utils.ThrowIfNil(end, http.StatusBadRequest, "Request path is required.")
			views, err := handler.controller.FindBetweenTime(start, end)
			utils.ThrowIfErr(err, http.StatusNotFound, "Views not found.")
			log.Debug("Enter: read all views information between ", start, end)
			ResponseSender(w, views, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ViewsHandler) FindViews(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			tokenHeader := r.Header.Get("Authorization")
			utils.ThrowIfNil(tokenHeader, http.StatusBadRequest, "Can't fetch a subject.")
			userId, err := utils.GetSubject(tokenHeader)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't fetch subject.")
			views, err := handler.controller.FindViews(userId)
			utils.ThrowIfErr(err, http.StatusNotFound, "Views not found.")
			log.Debug("Enter: read all views information by user id")
			ResponseSender(w, views, http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *ViewsHandler) CreateViews(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			tokenHeader := r.Header.Get("Authorization")
			utils.ThrowIfNil(tokenHeader, http.StatusBadRequest, "Can't fetch a subject.")
			userId, err := utils.GetSubject(tokenHeader)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't fetch subject.")
			body, err := ioutil.ReadAll(r.Body)
			defer r.Body.Close()
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize body")
			productId, err := strconv.ParseUint(string(body), BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Arguments must be a number.")
			err = handler.controller.CreateView(userId, uint(productId))
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't create Views.")
			log.WithFields(log.Fields{"productId": productId}).
				Debug("Enter: create new view by user id, Product Id ->")
			ResponseSender(w, "", http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
