package com.kotlin.weathercast.data

import java.util.*

/**
 * Created by nanjui on 2016. 12. 25..
 */

data class CityArray(val city: ArrayList<CityData>)
data class CityData(val _id:String, val name:String)