package com.github.deliveries.services;

import com.github.deliveries.entity.NovaposhtaLegalCounterparty;

public interface INovaposhtaLegalCounterpartyService {

    NovaposhtaLegalCounterparty create(NovaposhtaLegalCounterparty nlc);

    NovaposhtaLegalCounterparty read();

    void update(NovaposhtaLegalCounterparty nkc);

    void remove(Long id);

}
