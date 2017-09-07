package com.kotlin.weathercast

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlin.weathercase.R
import com.kotlin.weathercast.data.WeekList
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nanjui on 2016. 12. 24..
 */
class ForecastView: LinearLayout {
    var mainView: View? = null
    var scrollView: HorizontalScrollView? = null

    constructor(context: Context):super(context)
    constructor(context: Context, attributes: AttributeSet):super(context, attributes)

    init {
        mainView = LinearLayout(context)
        scrollView = HorizontalScrollView(context)
        scrollView?.scrollBarSize = 2
        scrollView?.addView(mainView)
        addView(scrollView)
    }

    private fun getForecastDate(time:Long):String
    {
        val format: SimpleDateFormat = SimpleDateFormat("dd일 HH시", Locale.KOREA)
        return format.format(time*1000L)
    }

    private fun createItemView(): View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(R.layout.layout_week_weather_view, null, false)
    }

    private fun getDataIndex(data: ArrayList<WeekList>):Int{
        val current:Long = Date().time
        (0..data.size -1).forEach { i->
            if(current < data[i].dt.toLong())
            {
                return i
            }
        }
        return 0;
    }

    fun setView(data: ArrayList<WeekList>, icon_url:String)
    {
        val start = getDataIndex(data)
        for(i in start..(start+15)) {
            val layout: View = createItemView()

            val week: TextView = layout.findViewById(R.id.week) as TextView
            week.text = getForecastDate(data[i].dt.toLong())

            val icon: ImageView = layout.findViewById(R.id.weather_icon) as ImageView
            icon.loadUrl(icon_url+data[i].weather[0].icon+".png")

            val temp: TextView = layout.findViewById(R.id.avg_temp) as TextView
            temp.text = "${data[i].main.temp} \\u2103"

            (mainView as LinearLayout).addView(layout)
        }
    }
}
