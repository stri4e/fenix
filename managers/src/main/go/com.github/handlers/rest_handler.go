package handlers

import (
	"../config"
	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
	"net/http"
)

type RestHandler struct {
	ordersHandler      *PurchasesHandler
	userOrderHandler   *UserOrderHandler
	managerHandler     *ManagerHandler
	categoryHandler    *CategoryHandler
	productHandler     *ProductHandler
	specHandler        *SpecificationHandler
	subcategoryHandler *SubcategoryHandler
	filterHandler      *FilterHandler
	criteriaHandler    *CriteriaHandler
	config             *config.Config
	tracer             *Tracer
}

func NewRestHandler(
	ordersHandler *PurchasesHandler,
	userOrderHandler *UserOrderHandler,
	managerHandler *ManagerHandler,
	categoryHandler *CategoryHandler,
	productHandler *ProductHandler,
	specHandler *SpecificationHandler,
	subcategoryHandler *SubcategoryHandler,
	filterHandler *FilterHandler,
	criteriaHandler *CriteriaHandler,
	config *config.Config,
	tracer *Tracer) *RestHandler {
	return &RestHandler{
		ordersHandler:      ordersHandler,
		userOrderHandler:   userOrderHandler,
		managerHandler:     managerHandler,
		categoryHandler:    categoryHandler,
		productHandler:     productHandler,
		specHandler:        specHandler,
		subcategoryHandler: subcategoryHandler,
		filterHandler:      filterHandler,
		criteriaHandler:    criteriaHandler,
		config:             config,
		tracer:             tracer,
	}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1", handler.ordersHandler.SavePurchase).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/{status}", handler.ordersHandler.FindPurchasesByStatus).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/all/{status}", handler.ordersHandler.FindPurchasesAllByStatus).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/{orderId}/{status}", handler.ordersHandler.UpdateStatusPurchase).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/binding/{orderId}", handler.userOrderHandler.FindBindingOrders).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/fetch/{orderId}", handler.managerHandler.FindManagerByOrder).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/category/{categoryName}", handler.categoryHandler.FindByCategoryName).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/category", handler.categoryHandler.SaveCategory).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/category", handler.categoryHandler.UpdateCategory).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/products/{subcategoryName}", handler.productHandler.SaveProduct).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/products/{productId}", handler.productHandler.FindProductById).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/products/un-publish", handler.productHandler.FindAllProduct).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/products", handler.productHandler.UpdateProduct).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/products/{productId}/{status}", handler.productHandler.UpdateStatusProduct).
		Methods(http.MethodDelete)
	router.HandleFunc("/v1/specifications/{productId}", handler.specHandler.SaveSpecification).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/specifications/{specificationId}", handler.specHandler.FindSpecById).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/specifications", handler.specHandler.UpdateSpecification).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/subcategory/{categoryName}", handler.subcategoryHandler.SaveSubcategory).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/subcategory/{subcategoryName}", handler.subcategoryHandler.FindSubcategoryByName).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/subcategory", handler.subcategoryHandler.UpdateSubcategory).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/filters/{subcategoryName}", handler.filterHandler.SaveFilter).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/filters/{filterId}", handler.filterHandler.FindFilterById).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/filters", handler.filterHandler.UpdateFilter).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/criteria/to/filters/{filterId}", handler.criteriaHandler.SaveToFilter).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/criteria/to/products/{productId}", handler.criteriaHandler.SaveToProducts).
		Methods(http.MethodPost)
	router.HandleFunc("/v1/criteria/{criteriaId}", handler.criteriaHandler.FindCriteriaById).
		Methods(http.MethodGet)
	router.HandleFunc("/v1/criteria", handler.criteriaHandler.UpdateCriteria).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/criteria/in/product/{productId}/{criteriaId}", handler.criteriaHandler.UpdateInProduct).
		Methods(http.MethodPut)
	router.HandleFunc("/v1/criteria/in/filters/{filterId}/{criteriaId}", handler.criteriaHandler.UpdateInFilters).
		Methods(http.MethodPut)

	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	if handler.config.ZipkinEnable {
		zipkinMiddleware := handler.tracer.CreateMiddleware()
		router.Use(zipkinMiddleware)
	}
	cors := handlers.CORS(
		handlers.AllowedHeaders([]string{"content-type"}),
		handlers.AllowedOrigins([]string{"*"}),
		handlers.AllowCredentials(),
	)
	router.Use(cors)
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}
