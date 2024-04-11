package com.narektm.weatherdashboard.service.city;

import com.fasterxml.jackson.databind.JsonNode;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.entity.CountryNameEmbeddable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The RestCountriesDataFetcher class fetches data of all countries
 * from the RestCountries API (<a href="https://restcountries.com/">...</a>)
 */
@Service
public class RestCountriesDataFetcher implements CityDataFetcher {

    private static final String URL = "https://restcountries.com/v3.1/all";

    private final RestClient restClient;

    public RestCountriesDataFetcher(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<CountryEntity> fetch() {
        ResponseEntity<JsonNode> response = restClient.get()
                .uri(URL)
                .retrieve()
                .toEntity(JsonNode.class);

        if (response.getBody() != null) {
            return processCountries(response.getBody());
        }

        return Collections.emptyList();
    }

    private List<CountryEntity> processCountries(JsonNode countryNodes) {
        List<CountryEntity> countries = new ArrayList<>();

        if (countryNodes.isArray()) {
            for (JsonNode countryNode : countryNodes) {
                countries.add(mapCountryNodeToEntity(countryNode));
            }
        }

        return countries;
    }

    private CountryEntity mapCountryNodeToEntity(JsonNode countryNode) {
        CountryEntity country = new CountryEntity();

        country.setCca2(countryNode.path("cca2").asText());
        country.setCcn3(countryNode.path("ccn3").asText());
        country.setCca3(countryNode.path("cca3").asText());

        CountryNameEmbeddable name = new CountryNameEmbeddable();
        name.setCommon(countryNode.path("name").path("common").asText());
        name.setOfficial(countryNode.path("name").path("official").asText());

        // Extracting the first native name
        if (countryNode.path("name").path("nativeName").elements().hasNext()) {
            JsonNode firstNativeName = countryNode.path("name").path("nativeName").elements().next();
            name.setCommonNative(firstNativeName.path("common").asText());
        }

        country.setName(name);
        country.setCapitalCity(countryNode.path("capital").get(0).asText());
        country.setRegion(countryNode.path("region").asText());
        country.setSubRegion(countryNode.path("subregion").asText());
        country.setPhoneCode(countryNode.path("idd").path("suffixes").get(0).asText());
        country.setFlagUri(name.getCommon().toLowerCase() + ".png");

        return country;
    }
}
