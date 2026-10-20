package io.fireflysoftware.data.business.intelligence.infra.clients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fireflyframework.client.RestClient;
import org.fireflyframework.client.ServiceClient;
import org.fireflyframework.resilience.CircuitBreakerManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrbisRestClientConfiguration {

    @Bean
    public RestClient orbisRestClient(WebClient.Builder webClientBuilder,
                                      CircuitBreakerManager circuitBreakerManager,
                                      @Value("${orbis.base-url:https://api.bvdinfo.com}") String baseUrl) {

        var orbisMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)
                .build();

        WebClient webClient = webClientBuilder
                .baseUrl(baseUrl)
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(
                            new Jackson2JsonDecoder(orbisMapper, MediaType.APPLICATION_JSON));
                    configurer.defaultCodecs().jackson2JsonEncoder(
                            new Jackson2JsonEncoder(orbisMapper, MediaType.APPLICATION_JSON));
                })
                .build();

        return ServiceClient.rest("orbis")
                .baseUrl(baseUrl)
                .webClient(webClient)
                .circuitBreakerManager(circuitBreakerManager)
                .build();
    }
}
