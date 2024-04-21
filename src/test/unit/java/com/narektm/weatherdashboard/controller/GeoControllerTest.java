package com.narektm.weatherdashboard.controller;

import com.narektm.weatherdashboard.service.GeoService;
import com.narektm.weatherdashboard.service.city.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeoController.class)
class GeoControllerTest {

    private static final String COUNTRIES_URL = "/countries";
    private static final String CITIES_URL = "/cities";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeoService geoService;

    @MockBean
    private CityService cityService;

    @Test
    void testFetchAndStoreCountries() throws Exception {
        mockMvc.perform(post(COUNTRIES_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(geoService).fetchAndStoreCountries();
    }

    @Test
    void testFetchAndStoreCities() throws Exception {
        mockMvc.perform(post(CITIES_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(geoService).fetchAndStoreCities();
    }
}
