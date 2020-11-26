package com.github.deliveries.services;

import com.github.deliveries.entity.NovaposhtaInternetDocument;

public interface INovaposhtaInternetDocumentService {

    NovaposhtaInternetDocument create(NovaposhtaInternetDocument nid);

    NovaposhtaInternetDocument readByOrderId(Long orderId);

}
