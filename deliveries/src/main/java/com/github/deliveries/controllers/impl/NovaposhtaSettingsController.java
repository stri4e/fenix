package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.INovaposhtaSettingsController;
import com.github.deliveries.dto.NovaposhtaSettingsDto;
import com.github.deliveries.services.INovaposhtaSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.deliveries.utils.TransferObj.fromNovaposhtaSettings;
import static com.github.deliveries.utils.TransferObj.toNovaposhtaSettings;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/novaposhta")
public class NovaposhtaSettingsController implements INovaposhtaSettingsController {

    private final INovaposhtaSettingsService novaposhtaSettingsService;

    @Override
    public NovaposhtaSettingsDto save(NovaposhtaSettingsDto payload) {
        return fromNovaposhtaSettings(
                this.novaposhtaSettingsService.create(toNovaposhtaSettings(payload))
        );
    }

    @Override
    public NovaposhtaSettingsDto find() {
        return fromNovaposhtaSettings(this.novaposhtaSettingsService.read());
    }

    @Override
    public void update(NovaposhtaSettingsDto payload) {
       this.novaposhtaSettingsService.update(
               payload.getId(), payload.getBaseUrl(), payload.getApiKey()
       );
    }

    @Override
    public void remove(Long id) {
        this.novaposhtaSettingsService.remove(id);
    }

}
