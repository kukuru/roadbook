package com.kotlin.weathercast

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kotlin.weathercase.R
import com.kotlin.weathercast.data.CityData
import com.kotlin.weathercast.data.WeatherForecast
import com.kotlin.weathercast.db.DBHanlderAnko
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<ArrayList<WeatherForecast>> {

    val LOADER_ID = 101010
    var adapter: WeatherListViewAdapter? = null
    var mWeatherData: ArrayList<WeatherForecast>? = null
    val mCityArray  = ArrayList<CityData>()
    val mDb = DBHanlderAnko(this)

    companion object{
        val SELECTED_CITY = 1100
        val REQUEST_CITY = 1101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCityArray.addAll(mDb.getCityDataAll())
        supportLoaderManager.initLoader(LOADER_ID, null, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.add_city->{
                val intent = Intent(this, SelectCityActivity::class.java)
                startActivityForResult(intent, REQUEST_CITY)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLoadFinished(loader: Loader<ArrayList<WeatherForecast>>?, data: ArrayList<WeatherForecast>) {
        adapter?:let{
            adapter = WeatherListViewAdapter(this, data)
            adapter?.setDeleteClickListener(){
                view -> val db = DBHanlderAnko(this)
                db.deleteCity(view.tag as String)
                adapter?.removeData(view.tag as String)
            }
            weatherList.adapter = adapter
            weatherList.layoutManager = LinearLayoutManager(this)
            mWeatherData = data
        }

        mWeatherData?.addAll(data)
        adapter?.updateData(data)
        progressbar.visibility = View.GONE
    }

    override fun onLoaderReset(loader: Loader<ArrayList<WeatherForecast>>?) {

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<ArrayList<WeatherForecast>> {
        val loader: ForecastDataLoader = ForecastDataLoader(this, mCityArray)
        loader.forceLoad()
        progressbar.visibility = View.VISIBLE
        return loader
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode)
        {
            SELECTED_CITY ->{
                mCityArray.clear()
                mCityArray.addAll(mDb.getCityDataAll())
                supportLoaderManager.restartLoader(LOADER_ID, null, this)
            }
        }
    }
}
