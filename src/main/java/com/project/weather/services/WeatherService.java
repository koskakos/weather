package com.project.weather.services;

//import com.project.weather.entities.WeatherEntity;
//import com.project.weather.repositories.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private static final String API_KEY = "637d1a6430609a403b361d720a8c3197";
    private static final String CELSIUS = "&units=metric";
    private static final String FAHRENHEIT = "&units=imperial";
    private RestTemplate restTemplate;
    private String url;
    //private final WeatherRepository weatherRepository;
    //

    public ResponseEntity getWeather(String city){
        try {
            url = new String("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + API_KEY + CELSIUS);
            ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
//            if(weatherRepository.existsByCity(weatherEntity.getCity())) weatherRepository.save(weatherEntity);
//            else {
//                WeatherEntity t = weatherRepository.findByCity(weatherEntity.getCity());
//                t.setId(t.getId()+1);
//            }

            return ResponseEntity.ok(result.getBody());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body("ERROR");
        }
    }
}
