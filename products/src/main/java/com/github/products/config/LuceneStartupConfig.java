package com.github.products.config;

import com.github.products.exceptions.FullTextSearchInitializeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@Slf4j
@Configurable
public class LuceneStartupConfig {

    @Bean
    public ApplicationListener<ApplicationReadyEvent> springStartUpAndCreateIndexes(EntityManager entityManager) {
        return e -> {
            try {
                FullTextEntityManager fullTextEntityManager =
                        Search.getFullTextEntityManager(entityManager);
                fullTextEntityManager.createIndexer().startAndWait();
            } catch (InterruptedException ex) {
                throw new FullTextSearchInitializeException(ex.getMessage());
            }
        };
    }

}
