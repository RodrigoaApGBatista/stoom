package com.rodrigobatista.controller.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResult {

        private List<GeocodeObject> results;
        private String status;
}
