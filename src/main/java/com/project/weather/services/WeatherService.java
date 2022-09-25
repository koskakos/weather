package com.project.weather.services;

import com.project.weather.entities.WeatherEntity;
import com.project.weather.repositories.WeatherRepository;
import com.project.weather.entities.WeatherEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final RestTemplate restTemplate = new RestTemplate();
    private String url;
    private final WeatherRepository weatherRepository;


    public ResponseEntity getWeather(String city){
        try {
            url = new String("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + API_KEY);
            ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
            addToDataBase(city);
            return ResponseEntity.ok(result.getBody());
        } catch (Exception ex){
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    public void addToDataBase(String city){
        if(!weatherRepository.existsByCity(city)) {
            WeatherEntity weatherEntity = new WeatherEntity();
            weatherEntity.setCity(city);
            weatherRepository.save(weatherEntity);
        }
        else {
            incrementCount(city);
        }
    }

    private void incrementCount(String city){
        WeatherEntity t = weatherRepository.findByCity(city);
        t.setCount(t.getCount()+1);
        weatherRepository.save(t);
    }

    public ResponseEntity getInfoFromDataBase(){
        //weatherRepository.findByOrderByCount();
        return ResponseEntity.ok(weatherRepository.findAll(Sort.by(Sort.Direction.DESC, "count")));
        //return ResponseEntity.ok(weatherRepository.find)
    }
}
