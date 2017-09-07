package com.kotlin.weathercast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.kotlin.weathercase.R
import com.kotlin.weathercast.data.CityData
import java.util.*

class CityListAdapter(context: Context, cityData: ArrayList<CityData>)
    : ArrayAdapter<CityData>(context, R.layout.layout_city_list, cityData) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var mainView = convertView
        mainView = mainView?:inflater.inflate(R.layout.layout_city_list, parent, false)

        val name : TextView = mainView!!.findViewById(R.id.city_name) as TextView

        val data :CityData = getItem(position)
        name.text = data.name
        mainView.tag = data._id

        return mainView!!
    }
}