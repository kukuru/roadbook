package com.kotlin.weathercast.data

import java.util.*

/**
 * Created by nanjui on 2016. 12. 17..
 */
data class DayData(val weather: ArrayList<WeatherData>, val main: MainData, val wind: WindData,
                   val clouds: CloudData, var cityName:String, var api_id:String)
{
    operator fun get(poisition:Int):WeatherData = weather[poisition]
}
data class WeekData(val list: ArrayList<WeekList>)

data class WeekList(val dt:String, val weather: ArrayList<WeatherData>, val main: MainData,
                    val clouds: CloudData, val dt_txt:String)

data class WeatherData(val main:String, val description:String, val icon:String)
data class MainData(val temp:String, val temp_min:String, val temp_max:String, val humidity:String)
data class CloudData(val all:String)

data class WindData(val speed:String)
data class WeatherForecast(val current: DayData, val week: WeekData, val iconUrl:String)


