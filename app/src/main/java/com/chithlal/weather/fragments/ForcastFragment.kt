package com.chithlal.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chithlal.weather.R
import com.chithlal.weather.model.Forecast


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

    companion object {

        @JvmStatic
        fun newInstance(forecast: Forecast) =
            ForcastFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_FORECAST, forecast)
                }
            }
    }
}