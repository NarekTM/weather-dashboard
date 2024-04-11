package com.narektm.weatherdashboard.service.city;

import com.fasterxml.jackson.databind.JsonNode;
import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * The GeographyApiCityDataFetcher class fetches all major cities of a country
 * from the Geography API (<a href="https://apilayer.com/marketplace/geo-api">...</a>)
 */
@Service
public class GeographyApiCityDataFetcher implements CityDataFetcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeographyApiCityDataFetcher.class);

    private static final String MAJOR_CITIES_OF_COUNTRY_URL_PATTERN = "https://api.apilayer.com/geo/country/cities/%s";

    private final RestClient restClient;

    private final String apiKey;

    public GeographyApiCityDataFetcher(RestClient restClient,
                                       @Value("${api-layer.geography.api-key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    @Override
    public List<CityEntity> fetch(CountryEntity country) {
        String countryCca2 = country.getCca2();
        String url = String.format(MAJOR_CITIES_OF_COUNTRY_URL_PATTERN, countryCca2);
        ResponseEntity<JsonNode> responseEntity = restClient.get()
                .uri(url)
                .header("apikey", apiKey)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) ->
                        LOGGER.info("No city found (countryCode: {}", countryCca2)))
                .toEntity(JsonNode.class);

        JsonNode responseBody = responseEntity.getBody();
        if (nonNull(responseBody)) {
            return process(responseBody);
        }

        return Collections.emptyList();
    }

    private List<CityEntity> process(JsonNode cityNodes) {
        List<CityEntity> cities = new ArrayList<>();

        if (cityNodes.isArray()) {
            for (JsonNode cityNode : cityNodes) {
                cities.add(mapCityNodeToEntity(cityNode));
            }
        }

        return cities;
    }

    private CityEntity mapCityNodeToEntity(JsonNode cityNode) {
        CityEntity city = new CityEntity();

        city.setGeoId(cityNode.path("geo_id").asText());
        city.setName(cityNode.path("name").asText());
        city.setStateOrRegion(cityNode.path("state_or_region").asText());
        city.setLatitude(cityNode.path("latitude").asDouble());
        city.setLongitude(cityNode.path("longitude").asDouble());

        return city;
    }
}
