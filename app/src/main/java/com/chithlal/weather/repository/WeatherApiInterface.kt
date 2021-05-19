package com.chithlal.weather.repository

import com.chithlal.weather.model.Forecast
import com.chithlal.weather.model.Temperature
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("weather")
    suspend fun getWeather(@Query("q") location: String,
    @Query("APPID") appId: String): Response<Temperature>

    @GET("forecast")
    suspend fun getForecast(@Query("q") location: String,
                            @Query("APPID") appId: String): Response<Forecast>
}