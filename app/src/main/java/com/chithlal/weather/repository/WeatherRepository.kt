package com.chithlal.weather.repository

import com.chithlal.weather.model.Forecast
import com.chithlal.weather.model.Temperature
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor( private val apiInterface: WeatherApiInterface) {

    suspend fun getWeather(loc: String,appId: String): Response<Temperature>{
        return apiInterface.getWeather(loc,appId)
    }

    suspend fun getForecast(loc: String,appId: String): Response<Forecast>{
        return apiInterface.getForecast(loc,appId)
    }
}