package com.github.messenger.config;

import infobip.api.client.CreateScenario;
import infobip.api.client.GetAccountBalance;
import infobip.api.client.SendAdvancedOmniMessage;
import infobip.api.client.SendSingleTextualSms;
import infobip.api.config.ApiKeyAuthConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value(value = "${app.sms.apiKey}")
    private String apiKey;

    @Bean
    public SendSingleTextualSms sendSingleTextualSms() {
        return new SendSingleTextualSms(new ApiKeyAuthConfiguration(this.apiKey));
    }

    @Bean
    public SendAdvancedOmniMessage sendAdvancedOmniMessage() {
        return new SendAdvancedOmniMessage(new ApiKeyAuthConfiguration(this.apiKey));
    }

    @Bean
    public GetAccountBalance getAccountBalance() {
        return new GetAccountBalance(new ApiKeyAuthConfiguration(this.apiKey));
    }

    @Bean
    public CreateScenario createScenario() {
        return new CreateScenario(new ApiKeyAuthConfiguration(this.apiKey));
    }

}
