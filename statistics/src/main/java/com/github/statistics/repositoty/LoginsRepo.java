package com.github.statistics.repositoty;

import com.github.statistics.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LoginsRepo extends JpaRepository<Login, Long> {

    Long countAllByCreateAtBetween(LocalDateTime now, LocalDateTime hourAgo);

}
