package com.github.products.repository;

import com.github.products.entity.*;
import com.github.products.utils.ProductSpec;
import com.github.products.utils.SpecificationSpec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
public class ProductRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SpecificationRepo specificationRepo;

    @Autowired
    private BrandRepo brandRepo;

}