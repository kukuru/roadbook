package com.test.weathercast.data

/**
 * Created by NanKuru on 2017-08-10.
 */
import com.google.gson.annotations.SerializedName
import java.util.*
data class DayData(val weather: ArrayList<WeatherData>, val main: MainData, val
wind: WindData,
                   val clouds: CloudData, val rain:RainData, var cityName:String,
var api_id:String)
{
    operator fun get(position:Int):WeatherData = weather[position]
}
data class WeekData(val list: ArrayList<WeekList>)
data class WeekList(val dt:String, val weather: ArrayList<WeatherData>, val main:
MainData,
                    val clouds: CloudData, val dt_txt:String)
data class WeatherData(val main:String, val description:String, val icon:String)
data class MainData(val temp:String, val temp_min:String, val temp_max:String, val
humidity:String)
data class CloudData(val all:String)
data class RainData(@SerializedName("3h")val rain:Int)
data class WindData(val speed:String)
data class WeatherForecast(val current: DayData, val week: WeekData, val
iconUrl:String)