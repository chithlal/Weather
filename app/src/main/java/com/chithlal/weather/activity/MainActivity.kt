package com.chithlal.weather.activity

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.chithlal.weather.R
import com.chithlal.weather.fragments.ForcastFragment
import com.chithlal.weather.model.Forecast
import com.chithlal.weather.utlity.Constants
import com.chithlal.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_screen.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTemperatureView()
        setupForecastView()

        bt_retry.setOnClickListener {
            showLoading(true)
            Executors.newSingleThreadScheduledExecutor().schedule({
                weatherViewModel.getWeather()
                weatherViewModel.getForecast()
            }, 2, TimeUnit.SECONDS)
        }
    }


    override fun onResume() {
        super.onResume()
        showLoading(true)
        Executors.newSingleThreadScheduledExecutor().schedule({
            weatherViewModel.getWeather()
            weatherViewModel.getForecast()
        }, 2, TimeUnit.SECONDS)


    }

    private fun setupTemperatureView() {

        weatherViewModel.weatherLiveData.observe(this, Observer {
            if (it != null) {
                showError(false)
                val currTemp = it.main.temp
                tvTemperature.text = Constants.convertKToC(currTemp)
                tvPlace.text = Constants.location
                showLoading(false)
            } else {
                showLoading(false)
                showError(true)
            }
        })
        weatherViewModel.weatherErrorLiveData.observe(this, Observer {
            showLoading(false)
            showError(true)
        })


    }

    private fun setupForecastView() {

        weatherViewModel.forecastLiveData.observe(this, Observer {
            if (it != null) {
                showForecastData(it)
            } else {
                showLoading(false)
                showError(true)
            }
        })
        weatherViewModel.forecastErrorLiveData.observe(this, Observer {
            showLoading(false)
            showError(true)
        })
    }

    private fun showForecastData(forecasts: Forecast) {
        showLoading(false)
        val fragmentManager = supportFragmentManager
        val forecastFragment = ForcastFragment.newInstance(forecasts)
        fragmentManager.commit {
            setCustomAnimations(
                R.anim.framgent_enter_animation,
                R.anim.framgent_enter_animation,
                R.anim.framgent_enter_animation,
                R.anim.framgent_enter_animation
            )

            replace(R.id.container_forcast_frag, forecastFragment)
        }
    }

    private fun showError(isError: Boolean) {
        if (isError) {
            error_layout.visibility = View.VISIBLE
            llTempLayout.visibility = View.GONE
            rlLoadlinLayout.visibility = View.GONE
        } else {
            error_layout.visibility = View.GONE
            llTempLayout.visibility = View.VISIBLE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            rlLoadlinLayout.visibility = View.VISIBLE
            llTempLayout.visibility = View.GONE
            error_layout.visibility = View.GONE
            val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
            img_loading.startAnimation(rotateAnim)
        } else {
            rlLoadlinLayout.visibility = View.GONE

        }
    }
}