package com.github.products.config;

import com.github.products.services.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ControllerApplicationContextConfig {

    @Bean
    public IBrandService brandService() {
        return Mockito.mock(IBrandService.class);
    }

    @Bean
    public ICategoryService categoryService() {
        return Mockito.mock(ICategoryService.class);
    }

    @Bean
    public ICommentService commentService() {
        return Mockito.mock(ICommentService.class);
    }

    @Bean
    public ICriteriaService criteriaService() {
        return Mockito.mock(ICriteriaService.class);
    }

    @Bean
    public IFiltersService filtersService() {
        return Mockito.mock(IFiltersService.class);
    }

    @Bean
    public IProductService productService() {
        return Mockito.mock(IProductService.class);
    }

    @Bean
    public ISpecificationService specificationService() {
        return Mockito.mock(ISpecificationService.class);
    }

    @Bean
    public IStocksService stocksService() {
        return Mockito.mock(IStocksService.class);
    }

    @Bean
    public ISubcategoryService subcategoryService() {
        return Mockito.mock(ISubcategoryService.class);
    }

}
