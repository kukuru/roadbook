package com.kotlin.weathercast

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.google.gson.Gson
import com.kotlin.weathercast.data.CityData
import com.kotlin.weathercast.data.DayData
import com.kotlin.weathercast.data.WeatherForecast
import com.kotlin.weathercast.data.WeekData
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class ForecastDataLoader(context: Context, val cities: ArrayList<CityData>)
    : AsyncTaskLoader<ArrayList<WeatherForecast>>(context) {
    val API_KEY : String = "2677d0da8acab2b1d91d54942d6914c8"
    val Current_URL : String = "http://api.openweathermap.org/data/2.5/weather?id="
    val Forcast_URL : String = "http://api.openweathermap.org/data/2.5/forecast?id="
    val ICON_URL : String = "http://openweathermap.org/img/w/"

    override fun loadInBackground(): ArrayList<WeatherForecast> {
        var city_weather = ArrayList<WeatherForecast>()

        with(cities.size > 0){
            cities.forEach { cityData->
                val cur_url= Current_URL+cityData._id+"&units=metric&APPID=$API_KEY"
                val readData = URL(cur_url).readText()
                var current: DayData = Gson().fromJson(readData, DayData::class.java)
                val fore_url = Forcast_URL+cityData._id+"&units=metric&APPID=$API_KEY"
                val url = URL(fore_url)
                val inputstream = InputStreamReader(url.openStream())

                val week: WeekData = Gson().fromJson(inputstream, WeekData::class.java)
                val forcast: WeatherForecast = WeatherForecast(current, week, ICON_URL)
                city_weather.add(forcast)
            }
        }
        return city_weather
    }
}