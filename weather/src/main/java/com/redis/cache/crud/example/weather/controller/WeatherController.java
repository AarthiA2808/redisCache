package com.redis.cache.crud.example.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.redis.cache.crud.example.weather.entity.Weather;
import com.redis.cache.crud.example.weather.repository.WeatherDetailsRepo;
import com.redis.cache.crud.example.weather.service.WeatherDetailsService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherDetailsService weatherDetailService;

	@Autowired
	private WeatherDetailsRepo weatherDetailsRepo;

	@GetMapping
	public String getweather(String city) {
		return weatherDetailService.getWeatherByCity(city);
	}

	@PostMapping
	public Weather addWeather(@RequestBody Weather weather) {
		return weatherDetailsRepo.save(weather);
	}
	
    @GetMapping("/all")
    public List<Weather> getAllWeather() {
        return weatherDetailsRepo.findAll();
    }
    
    @PutMapping("/{city}")
    public String updateWeather(@PathVariable String city, @RequestParam String weatherUpdate) {
        return weatherDetailService.updateWeather(city, weatherUpdate);
    }
    
    @DeleteMapping("/{city}")
    public String deleteWeather(@PathVariable String city) {
    	weatherDetailService.deleteWeather(city);
        return "Weather data for " + city + " has been deleted and cache evicted.";
    }

}
