package com.github.payments.repository;

import com.github.payments.entity.Whom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhomRepo extends JpaRepository<Whom, Long> {
}
