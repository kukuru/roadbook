package com.test.weathercast.data

import java.util.*

/**
 * Created by NanKuru on 2017-08-10.
 */
data class CityArray(val city: ArrayList<CityData>)
data class CityData(val _id:String, val name:String)