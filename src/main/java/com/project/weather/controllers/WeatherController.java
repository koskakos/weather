package com.project.weather.controllers;

//import com.project.weather.entities.WeatherEntity;
import com.project.weather.entities.WeatherEntity;
import com.project.weather.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @ResponseBody
    @PostMapping("/getweather")
    public ResponseEntity getWeather(@RequestParam(name = "city")String city){
        return weatherService.getWeather(city);
    }

    @ResponseBody
    @GetMapping("/database")
    public ResponseEntity getDataBase(){
        return weatherService.getInfoFromDataBase();
    }
    /*@GetMapping ("/test")
    @ResponseBody
    public ResponseEntity test(String){
        return
    }*/
}
