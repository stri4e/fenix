package com.github.products.config;

import com.github.products.repository.*;
import com.github.products.services.*;
import com.github.products.services.impl.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceApplicationContextConfig {

    @MockBean
    private BrandRepo brandRepo;

    @MockBean
    private CategoryRepo categoryRepo;

    @MockBean
    private CommentRepo commentRepo;

    @MockBean
    private CriteriaRepo criteriaRepo;

    @MockBean
    private FiltersRepo filtersRepo;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private SpecificationRepo specificationRepo;

    @MockBean
    private StocksRepo stocksRepo;

    @MockBean
    private SubcategoryRepo subcategoryRepo;

    @MockBean
    private SpecSectionRepo specSectionRepo;

    @Bean
    public IBrandService brandService() {
        return new BrandService(this.brandRepo);
    }

    @Bean
    public ICategoryService categoryService() {
        return new CategoryService(this.categoryRepo);
    }

    @Bean
    public ICommentService commentService() {
        return new CommentService(this.commentRepo);
    }

    @Bean
    public ICriteriaService criteriaService() {
        return new CriteriaService(this.criteriaRepo);
    }

    @Bean
    public IFiltersService filtersService() {
        return new FiltersService(this.filtersRepo);
    }

    @Bean
    public IProductService productService() {
        return new ProductService(this.productRepo);
    }

    @Bean
    public ISpecificationService specificationService() {
        return new SpecificationService(this.specificationRepo);
    }

    @Bean
    public IStocksService stocksService() {
        return new StocksService(this.stocksRepo);
    }

    @Bean
    public ISubcategoryService subcategoryService() {
        return new SubcategoryService(this.subcategoryRepo);
    }

    @Bean
    public ISpecSectionService specSectionService() {
        return new SpecSectionService(this.specSectionRepo);
    }

}
