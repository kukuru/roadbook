package com.test.weathercast

/**
 * Created by NanKuru on 2017-08-10.
 */
import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.google.gson.Gson
import com.test.weathercast.data.CityData
import com.test.weathercast.data.DayData
import com.test.weathercast.data.WeatherForecast
import com.test.weathercast.data.WeekData
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
        val city_weather = ArrayList<WeatherForecast>()
        cities.forEach {
            val cur_url = Current_URL+it._id+"&units=metric&APPID=$API_KEY"
            val readData = URL(cur_url).readText()
            val current: DayData = Gson().fromJson(readData, DayData::class.java)
            current.cityName = it.name
            current.api_id = it._id
            val fore_url = Forcast_URL+it._id+"&units=metric&APPID=$API_KEY"
            val url = URL(fore_url)
            val inputstream = InputStreamReader(url.openStream())
            val week: WeekData = Gson().fromJson(inputstream, WeekData::class.java)
            val forcast: WeatherForecast = WeatherForecast(current, week, ICON_URL)
            city_weather.add(forcast)
        }
        return city_weather
    }
}