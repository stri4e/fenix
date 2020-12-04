package com.github.deliveries.repository;

import com.github.deliveries.entity.NovaposhtaInternetDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovaposhtaInternetDocumentRepo extends JpaRepository<NovaposhtaInternetDocument, Long> {

    Optional<NovaposhtaInternetDocument> findByOrderId(Long orderId);

}
