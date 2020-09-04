package handlers

import (
	"../controllers"
	"../utils"
	"encoding/binary"
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
			strUserId := vars["userId"]
			utils.ThrowIfNil(strUserId, utils.BadRequest{Message: "Request path is required."})
			userId, err := strconv.ParseUint(strUserId, BaseUint, BitSize)
			utils.ThrowIfErr(err, utils.BadRequest{Message: "Arguments must be a number."})
			products, err := handler.controller.FindByUserId(uint(userId))
			utils.ThrowIfErr(err, utils.NotFound{Message: "Views not found."})
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
			utils.ThrowIfNil(start, utils.BadRequest{Message: "Request path is required."})
			utils.ThrowIfNil(end, utils.BadRequest{Message: "Request path is required."})
			views, err := handler.controller.FindBetweenTime(start, end)
			utils.ThrowIfErr(err, utils.NotFound{Message: "Views not found."})
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
			utils.ThrowIfNil(tokenHeader, utils.BadRequest{Message: "Can't fetch a subject."})
			userId, err := utils.GetSubject(tokenHeader)
			utils.ThrowIfErr(err, utils.BadRequest{Message: "Can't fetch subject."})
			views, err := handler.controller.FindViews(userId)
			utils.ThrowIfErr(err, utils.NotFound{Message: "Views not found."})
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
			utils.ThrowIfNil(tokenHeader, utils.BadRequest{Message: "Can't fetch a subject."})
			userId, err := utils.GetSubject(tokenHeader)
			utils.ThrowIfErr(err, utils.BadRequest{Message: "Can't fetch subject."})
			body, err := ioutil.ReadAll(r.Body)
			defer r.Body.Close()
			utils.ThrowIfErr(err, utils.BadRequest{Message: "Can't deserialize body"})
			productId := uint(binary.BigEndian.Uint64(body))
			err = handler.controller.CreateView(userId, productId)
			utils.ThrowIfErr(err, utils.BadRequest{Message: "Can't create Views."})
			log.WithFields(log.Fields{"productId": productId}).
				Debug("Enter: create new view by user id, Product Id ->")
			ResponseSender(w, "", http.StatusCreated)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
