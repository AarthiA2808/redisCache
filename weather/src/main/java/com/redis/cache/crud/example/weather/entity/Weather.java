package com.redis.cache.crud.example.weather.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="weather")
public class Weather {
	@Id
	@GeneratedValue
	private long id;
	private String city;
	private String forecast;
	

	public Weather() {

	}
	
	public Weather(String city, String forecast) {
		
		this.city = city;
		this.forecast = forecast;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	

}
