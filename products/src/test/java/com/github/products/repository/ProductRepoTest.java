package com.github.products.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
public class ProductRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SubcategoryRepo subCategoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SpecificationRepo specificationRepo;

    @Autowired
    private BrandRepo brandRepo;

}