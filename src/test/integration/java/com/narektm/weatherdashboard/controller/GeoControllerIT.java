package com.narektm.weatherdashboard.controller;

import com.narektm.weatherdashboard.config.AbstractApiIntegrationTest;
import com.narektm.weatherdashboard.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

class GeoControllerIT extends AbstractApiIntegrationTest {

    @Autowired
    private CountryRepository countryRepository;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("rest-countries.base.url", () -> "http://localhost:" + wireMockExtension.getPort());
    }

    @Test
    void should_FetchAndStoreCountriesSuccessfully() {
        String uri = "/v3.1/all";
        wireMockExtension.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("countries_response.json")));

        ResponseEntity<Void> response = testRestTemplate.postForEntity(COUNTRIES_URL, null, Void.class);

        Awaitility.await()
                .pollDelay(200, TimeUnit.MILLISECONDS)
                .atMost(1, TimeUnit.SECONDS)
                .untilAsserted(() -> assertThat(countryRepository.findAll()).hasSize(4));

        wireMockExtension.verify(1, getRequestedFor(urlEqualTo(uri)));
        assertThat(countryRepository.findAll()).hasSize(4);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(202));
    }
}
