package com.redis.cache.crud.example.weather.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.cache.crud.example.weather.entity.Weather;
import com.redis.cache.crud.example.weather.repository.WeatherDetailsRepo;

import jakarta.transaction.Transactional;

@Service
public class WeatherDetailsService {
	
	private final WeatherDetailsRepo weatherDetailsRepo;
	
	public WeatherDetailsService(WeatherDetailsRepo weatherDetailsRepo) {
		this.weatherDetailsRepo=weatherDetailsRepo;
	}

	@Cacheable(value = "weather", key = "#city")
	public String getWeatherByCity(String city) {
		Optional<Weather> weather=weatherDetailsRepo.findByCity(city);
		return weather.map(Weather::getForecast).orElse("Weather forecast not available");
	}

	@CachePut(value = "weather", key = "#city")
	public String updateWeather(String city, String updatedWeather) {
		weatherDetailsRepo.findByCity(city).ifPresent(weather -> {
            weather.setForecast(updatedWeather 	);
            weatherDetailsRepo.save(weather);
        });
        return updatedWeather;
	}
	
	@CacheEvict
	@Transactional
	public void deleteWeather(String city) {
        System.out.println("Removing weather data for city: " + city);
        weatherDetailsRepo.deleteByCity(city);
    }
}
