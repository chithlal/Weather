package com.chithlal.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chithlal.weather.R
import com.chithlal.weather.model.Forecast
import com.chithlal.weather.model.Temp
import kotlinx.android.synthetic.main.fragment_forcast.*


private const val ARG_FORECAST = "FORECAST"


class ForcastFragment : Fragment() {

    private var forecast: Forecast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            forecast = it.getSerializable(ARG_FORECAST) as Forecast


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forcast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (forecast != null) {
            setupForecast(forecast!!)
        }
    }

    private fun setupForecast(forecast: Forecast) {
        val tempertureList = forecast.list
        if (tempertureList.size >= 5) {
            val fourDayList = filterAndAvarageTempreture(tempertureList)
            rv_forecastList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ForecastAdapter(context, fourDayList)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        } else {
            Toast.makeText(context, "Not enough data to forecast!", Toast.LENGTH_SHORT).show()
        }
    }


    /**
    * returns the average temperature list for the coming 4 days
    * */
    private fun filterAndAvarageTempreture(tempertureList: List<Temp>): List<ForeCastTemperature> {

        val tempList = ArrayList<ForeCastTemperature>()
        var curDate = tempertureList[0].dt_txt.split(" ")[0]
        var latestTotalTemp = 0.0
        var totalTempCount = 0
        tempertureList.forEachIndexed { index, temp ->

            if (temp.dt_txt.contains(curDate)) {
                totalTempCount++
                latestTotalTemp += temp.main.temp
            } else if (!temp.dt_txt.contains(curDate) || index == tempertureList.size - 1) {
                tempList.add(ForeCastTemperature(curDate, latestTotalTemp / totalTempCount))
                curDate = temp.dt_txt.split(" ")[0]
                latestTotalTemp = 0.0
                totalTempCount = 0
            }
        }
        return tempList.subList(1, 5)
    }

    companion object {

        @JvmStatic
        fun newInstance(forecast: Forecast) =
            ForcastFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_FORECAST, forecast)
                }
            }
    }

    data class ForeCastTemperature(val date: String, val avgTemp: Double)
}