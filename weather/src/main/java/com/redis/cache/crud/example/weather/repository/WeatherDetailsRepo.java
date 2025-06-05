package com.redis.cache.crud.example.weather.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redis.cache.crud.example.weather.entity.Weather;

public interface WeatherDetailsRepo extends JpaRepository<Weather, Long> {
	Optional<Weather> findByCity(String city);
	void deleteByCity(String city);

}
