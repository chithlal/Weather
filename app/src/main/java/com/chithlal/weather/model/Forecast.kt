package com.chithlal.weather.model

import java.io.Serializable

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Temp>,
    val message: Int
): Serializable

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
): Serializable

data class Temp(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
): Serializable




data class Rain(
    val `3h`: Double
): Serializable


