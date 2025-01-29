package com.postcare.seguimiento_postoperatorio_ms.config;

import com.postcare.seguimiento_postoperatorio_ms.service.GatewayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TriageConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GatewayClient gatewayClient(
            RestTemplate restTemplate,
            @Value("${gateway.url}") String triageUrl
    ) {
        return new GatewayClient(restTemplate, triageUrl);
    }
}