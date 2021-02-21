package com.rodrigobatista.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigobatista.controller.geocode.GeocodeResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class GeocodeController {

    @RequestMapping(path = "/geocode", method = RequestMethod.GET )
    public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw")
                .build();
        ResponseBody responseBody = (ResponseBody) client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.toString(), GeocodeResult.class);
        return result;
    }

}
