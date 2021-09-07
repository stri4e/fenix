package com.github.products.repository.impl;

import com.github.products.entity.Product;
import com.github.products.repository.ProductSearchRepo;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductSearchRepoImpl implements ProductSearchRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings(value = "unchecked")
    public Page<Product> keyWorldsSearch(String text) {
        Query query = queryBuilder()
                .keyword()
                .onFields("name", "description")
                .matching(text)
                .createQuery();
        return new PageImpl<Product>(jpaQuery(query).getResultList());
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Page<Product> fuzzySearch(String text) {
        Query query = queryBuilder()
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onFields("name", "description")
                .matching(text)
                .createQuery();
        return new PageImpl<Product>(jpaQuery(query).getResultList());
    }

    private FullTextQuery jpaQuery(org.apache.lucene.search.Query luceneQuery) {
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(this.entityManager);
        return fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);
    }

    private QueryBuilder queryBuilder() {
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(this.entityManager);
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();
    }

}
