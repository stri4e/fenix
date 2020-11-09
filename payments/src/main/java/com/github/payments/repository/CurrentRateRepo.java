package com.github.payments.repository;

import com.github.payments.entity.CurrentRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrentRateRepo extends CrudRepository<CurrentRate, Long> {

    @Query(value = "select cr from CurrentRate cr where cr.createAt < ?1")
    List<CurrentRate> findAllByCreateAtLessThan(LocalDateTime createAt);

}
