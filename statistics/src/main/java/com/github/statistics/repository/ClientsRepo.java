package com.github.statistics.repository;

import com.github.statistics.entity.Client;
import com.github.statistics.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepo extends JpaRepository<Client, Long> {

    Long countAllByStatus(EntityStatus status);

}
