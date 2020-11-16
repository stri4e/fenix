package com.github.payments.repository;

import com.github.payments.entity.Who;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhoRepo extends JpaRepository<Who, Long> {
}
