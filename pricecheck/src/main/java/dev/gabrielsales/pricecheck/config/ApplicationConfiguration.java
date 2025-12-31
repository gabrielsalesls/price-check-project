package dev.gabrielsales.pricecheck.config;

import dev.gabrielsales.pricecheck.client.impl.ProviderAApiClient;
import dev.gabrielsales.pricecheck.client.impl.ProviderBApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProviderAApiClient providerAApiClient(@Value("${provider-a.url}") String baseUrl) {
        var client = RestClient.builder().baseUrl(baseUrl).build();
        return new ProviderAApiClient(client);
    }

    @Bean
    public ProviderBApiClient providerBApiClient(@Value("${provider-b.url}") String baseUrl) {
        var client = RestClient.builder().baseUrl(baseUrl).build();
        return new ProviderBApiClient(client);
    }

}
