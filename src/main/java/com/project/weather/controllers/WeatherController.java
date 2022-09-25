package com.project.weather.controllers;

//import com.project.weather.entities.WeatherEntity;
import com.project.weather.services.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
public class WeatherController {
    private WeatherService weatherService;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @ResponseBody
    @PostMapping("/getweather")
    public ResponseEntity getWeather(@RequestParam(name = "city") String city){
        return weatherService.getWeather(city);
    }

    /*@GetMapping ("/test")
    @ResponseBody
    public ResponseEntity test(String){
        return
    }*/
}
