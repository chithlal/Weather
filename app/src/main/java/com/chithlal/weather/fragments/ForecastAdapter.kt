package com.chithlal.weather.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chithlal.weather.R
import com.chithlal.weather.model.Temp
import com.chithlal.weather.utlity.Constants
import com.chithlal.weather.utlity.DateUtil
import kotlinx.android.synthetic.main.layout_forecast_item.view.*

class ForecastAdapter(val context: Context, val tempList: List<ForcastFragment.ForeCastTemperature>) :
 RecyclerView.Adapter<ForecastAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val day = view.tv_day
        val temp = view.tv_day_temp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_forecast_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ViewHolder, position: Int) {
        val temperature = tempList[position]
        holder.temp.text = Constants.convertKToC(temperature.avgTemp)
        holder.day.text = DateUtil.getDayFromDate(temperature.date)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }

}