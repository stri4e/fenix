package com.github.bitcoin.repository;

import com.github.bitcoin.entity.Block;
import com.github.bitcoin.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepo extends JpaRepository<Block, Long> {

    Block findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Block b set b.number=:number where b.status=:status")
    void update(
            @Param(value = "number") Long number,
            @Param(value = "status") EntityStatus status
    );

}
