package com.chithlal.weather.utlity

class Constants {

    companion object {
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        val location = "Bengaluru"
        val APPID = "9b8cb8c7f11c077f8c4e217974d9ee40"

        //convert Fahrenheit to Celsius
        fun convertKToC(f: Double): String {
            return "${(f -273.15).toInt() }°"
        }
    }
}