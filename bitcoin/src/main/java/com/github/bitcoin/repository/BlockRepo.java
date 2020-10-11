package com.github.bitcoin.repository;

import com.github.bitcoin.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepo extends JpaRepository<Block, Long> {
}
