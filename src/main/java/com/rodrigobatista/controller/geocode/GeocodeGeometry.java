package com.rodrigobatista.controller.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GeocodeGeometry {

    @JsonProperty("location")
    private GeocodeLocation geocodeLocation;

}
