package com.test.weathercast

/**
 * Created by NanKuru on 2017-08-10.
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.test.weathercast.data.WeatherForecast
import java.util.*
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val descript: TextView = itemView.findViewById(R.id.descript) as TextView
    val weatherIcon: ImageView = itemView.findViewById(R.id.weahter_icon) as ImageView
    val currentTemp: TextView = itemView.findViewById(R.id.current_temp) as TextView
    val highRowTemp: TextView = itemView.findViewById(R.id.high_row_temp) as TextView
    val cityName: TextView = itemView.findViewById(R.id.city_name) as TextView
    val humidity: TextView = itemView.findViewById(R.id.humidity) as TextView
    val cloudy: TextView = itemView.findViewById(R.id.cloudy) as TextView
    val wind: TextView = itemView.findViewById(R.id.wind) as TextView
    val forecast: ForecastView = itemView.findViewById(R.id.forecast) as ForecastView
    val delbtn: ImageButton = itemView.findViewById(R.id.del_btn) as ImageButton

    fun bindHolder(context:Context, data: WeatherForecast,
                   delClick: View.OnClickListener?)
    {
        descript.text = data.current[0].description
        weatherIcon.loadUrl(data.iconUrl + data.current[0].icon + ".png")
        currentTemp.text = String.format("%s \\u2103", data.current.main.temp)
        val format: String = "%s \\u2103 / %s \\u2103"
        highRowTemp.text = String.format(format, data.current.main.temp_min, data.
                current.main.temp_max)
        cityName.text = data.current.cityName
        cloudy.text = String.format("%s %%", data.current.clouds.all)
        humidity.text = String.format("%s %%", data.current.main.humidity)
        wind.text = data.current.wind.speed
        forecast.setView(data.week.list, data.iconUrl)
        delbtn.setOnClickListener(delClick)
        delbtn.tag = data.current.api_id
    }
}
fun ImageView.loadUrl(url:String)
{
    Picasso.with(context).load(url).into(this)
}
class WeatherListViewAdapter(val context: Context, val data:
ArrayList<WeatherForecast>)
    : RecyclerView.Adapter<ViewHolder>() {
    var mWeatherData = ArrayList<WeatherForecast>(data)
    var delBtnClickListener:View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mainView = inflater.inflate(R.layout.layout_card, parent, false)
        val viewHolder: ViewHolder = ViewHolder(mainView)
        return viewHolder
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val data = mWeatherData[position]
        holder?.bindHolder(context, data, delBtnClickListener)
    }
    fun setDeleteClickListener(onClick: (View)->Unit){
        delBtnClickListener = object : View.OnClickListener {
            override fun onClick(view: View) {
                onClick(view)
            }
        }
    }
    fun updateData(newData: ArrayList<WeatherForecast>){
        mWeatherData.clear()
        mWeatherData.addAll(newData)
        notifyDataSetChanged()
    }
    fun removeData(api_id:String)
    {
        for(i in mWeatherData)
        {
            if(i.current.api_id.equals(api_id)) {
                mWeatherData.remove(i)
                break
            }
        }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mWeatherData.size
}