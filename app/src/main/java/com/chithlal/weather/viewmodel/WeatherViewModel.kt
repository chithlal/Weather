package com.chithlal.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chithlal.weather.model.Forecast
import com.chithlal.weather.model.Temperature
import com.chithlal.weather.repository.WeatherRepository
import com.chithlal.weather.utlity.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepository) :
    ViewModel() {

    val weatherLiveData = MutableLiveData<Temperature>()
    val weatherErrorLiveData = MutableLiveData<String>()

    val forecastLiveData = MutableLiveData<Forecast>()
    val forecastErrorLiveData = MutableLiveData<String>()

    // get current weather
    fun getWeather(){
        viewModelScope.launch {
            weatherRepo.getWeather(Constants.location,Constants.APPID).let {
                if (it.isSuccessful){
                    weatherLiveData.postValue(it.body())
                }else{
                    weatherErrorLiveData.postValue("Error!")
                }
            }
        }
    }

    fun getForecast(){

        viewModelScope.launch {
            weatherRepo.getForecast(Constants.location,Constants.APPID).let {
                if (it.isSuccessful){
                    forecastLiveData.postValue(it.body())
                }
                else{
                    forecastErrorLiveData.postValue("Error!")
                }
            }
        }
    }
}