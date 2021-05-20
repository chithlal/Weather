package com.chithlal.weather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.chithlal.weather.R
import com.chithlal.weather.fragments.ForcastFragment
import com.chithlal.weather.model.Forecast
import com.chithlal.weather.utlity.Constants
import com.chithlal.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTemperatureView()
        setupForecastView()
    }



    override fun onResume() {
        super.onResume()

        weatherViewModel.getWeather()
        weatherViewModel.getForecast()
    }

    private fun setupTemperatureView() {

        weatherViewModel.weatherLiveData.observe(this, Observer {
            if (it != null) {
                showError(false)
                val currTemp = it.main.temp
                tvTemperature.text = Constants.convertKToC(currTemp)
                tvPlace.text = Constants.location
            }
            else{
                showError(true)
            }
        })
        weatherViewModel.weatherErrorLiveData.observe(this, Observer{
            showError(true)
        })


    }
    private fun setupForecastView() {

        weatherViewModel.forecastLiveData.observe(this, Observer {
            if(it != null){
                showForecastData(it)
            }
            else{
                showError(true)
            }
        })
        weatherViewModel.forecastErrorLiveData.observe(this, Observer {
            showError(true)
        })
    }

    private fun showForecastData(forecasts: Forecast) {

        val fragmentManager = supportFragmentManager
        val forecastFragment  = ForcastFragment.newInstance(forecasts)
        fragmentManager.commit {
            setCustomAnimations(R.anim.framgent_enter_animation, R.anim.framgent_enter_animation, R.anim.framgent_enter_animation, R.anim.framgent_enter_animation)

            replace(R.id.container_forcast_frag,forecastFragment)
        }
    }

    private fun showError(isError: Boolean){
        if (isError){
            error_layout.visibility = View.VISIBLE
            llTempLayout.visibility = View.GONE
            rlLoadlinLayout.visibility = View.GONE
        }
        else{
            error_layout.visibility = View.GONE
            llTempLayout.visibility = View.VISIBLE
        }
    }
    private fun showLoading(isLoading: Boolean){

    }
}