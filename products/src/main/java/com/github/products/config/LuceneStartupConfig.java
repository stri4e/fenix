package com.github.products.config;

import com.github.products.exceptions.FullTextSearchInitializeException;
import com.github.products.utils.Logging;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Configurable
public class LuceneStartupConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    @Logging
    public ApplicationListener<ApplicationReadyEvent> springStartUpAndCreateIndexes() {
        return e -> {
            try {
                FullTextEntityManager fullTextEntityManager =
                        Search.getFullTextEntityManager(this.entityManager);
                fullTextEntityManager.createIndexer().startAndWait();
            } catch (InterruptedException ex) {
                throw new FullTextSearchInitializeException(ex.getMessage());
            }
        };
    }

}
