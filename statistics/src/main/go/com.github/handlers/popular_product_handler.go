package handlers

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"net/http"
	"statistics/src/main/go/com.github/controllers"
	"statistics/src/main/go/com.github/utils"
	"strconv"
)

type PopularProductHandler struct {
	controller *controllers.PopularProductController
}

func NewPopularProductHandler(controller *controllers.PopularProductController) *PopularProductHandler {
	return &PopularProductHandler{controller: controller}
}

func (handler *PopularProductHandler) UpdatePercentView(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			vars := mux.Vars(r)
			strProductId := vars["productId"]
			utils.ThrowIfNil(strProductId, http.StatusBadRequest, "Request path is required.")
			productId, err := strconv.ParseUint(strProductId, BaseUint, BitSize)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize product id.")
			err = handler.controller.UpdatePercentView(uint(productId))
			utils.ThrowIfErr(err, http.StatusNotFound, "Logins not found.")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}

func (handler *PopularProductHandler) UpdatePercentBought(w http.ResponseWriter, r *http.Request) {
	utils.Block{
		Try: func() {
			var productIds []uint
			err := json.NewDecoder(r.Body).Decode(&productIds)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't deserialize payload.")
			err = handler.controller.UpdatePercentBought(productIds)
			utils.ThrowIfErr(err, http.StatusBadRequest, "Can't update statistics.")
			ResponseSender(w, "", http.StatusOK)
		}, Catch: func(e utils.Exception) {
			ErrorSender(w, e)
		},
	}.Do()
}
