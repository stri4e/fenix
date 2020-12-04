package com.github.bitcoin.repository;

import com.github.bitcoin.entity.UnspentOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnspentOutRepo extends JpaRepository<UnspentOut, Long> {

    UnspentOut readByIndexAndTrxHash(Integer index, String hash);

}
