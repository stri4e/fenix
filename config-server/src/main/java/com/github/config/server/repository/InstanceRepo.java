package com.github.config.server.repository;

import com.github.config.server.entity.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepo extends JpaRepository<Instance, Long> {
}
