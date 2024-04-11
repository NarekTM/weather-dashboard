package com.narektm.weatherdashboard.service.country;

import com.fasterxml.jackson.databind.JsonNode;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.entity.CountryNameEmbeddable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * The RestCountriesDataFetcher class fetches data of all countries
 * from the RestCountries API (<a href="https://restcountries.com">...</a>)
 */
@Service
public class RestCountriesDataFetcher implements CountryDataFetcher {

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

        JsonNode responseBody = response.getBody();
        if (nonNull(responseBody)) {
            return process(responseBody);
        }

        return Collections.emptyList();
    }

    private List<CountryEntity> process(JsonNode countryNodes) {
        List<CountryEntity> countries = new ArrayList<>();

        if (countryNodes.isArray()) {
            for (JsonNode countryNode : countryNodes) {
                Optional.ofNullable(countryNode.path("capital").get(0)).ifPresent(capitalCity ->
                        countries.add(mapCountryNodeToEntity(countryNode, capitalCity)));
            }
        }

        return countries;
    }

    private CountryEntity mapCountryNodeToEntity(JsonNode countryNode, JsonNode capitalCity) {
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
        country.setCapitalCity(capitalCity.asText());
        country.setRegion(countryNode.path("region").asText());
        country.setSubRegion(countryNode.path("subregion").asText());
        Optional.of(countryNode.path("idd")).ifPresent(idd -> country.setPhoneCode(getPhoneCode(idd)));
        country.setFlagUri(name.getCommon().toLowerCase() + ".png");

        return country;
    }

    private String getPhoneCode(JsonNode idd) {
        String root = idd.path("root").asText();
        String suffix = idd.path("suffixes").get(0).asText();

        return root + suffix;
    }
}
