package com.kotlin.nationinfo

import java.util.*

/**
 * Created by nanjui on 2016. 9. 11..
 */
data class NationDetailData (val name:String,
                             val capital:String,
                             val location:String,
                             val volume:String,
                             val weather:String,
                             val language:String)

data class GsonData(val data:ArrayList<NationDetailData>)