package com.kotlin.weathercast.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kotlin.weathercast.data.CityData
import org.jetbrains.anko.db.*
import java.util.*

/**
 * Created by nanjui on 2016. 11. 20..
 */
class DBHanlderAnko(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_Version) {

    companion object CityTable{
        val DB_Name = "user.db"
        val DB_Version = 1;
        val TABLE_NAME = "city"
        val ID = "_id"
        val NAME = "name"
        val API_ID = "api_id"
    }

    fun getCityDataAll(): ArrayList<CityData> {
        val data = ArrayList<CityData>()
        val cursor = readableDatabase.query(CityTable.TABLE_NAME,
                arrayOf(CityTable.ID, CityTable.NAME, CityTable.API_ID),
                null, null, null, null, null)
        if(cursor.count == 0)
            return data

        cursor.moveToFirst()
        do
        {
            val city: CityData = CityData(cursor.getString(2), cursor.getString(1))
            data.add(city)
        }while(cursor.moveToNext())
        return data
    }

    fun saveCity(city: CityData)
    {
        var info = ContentValues()
        info.put(CityTable.NAME, city.name)
        info.put(CityTable.API_ID, city._id)

        writableDatabase.use {
            writableDatabase.insert(CityTable.TABLE_NAME, null, info)
        }
    }

    fun deleteCity(id:String)
    {
        writableDatabase.use {
            writableDatabase.execSQL("DELETE FROM ${CityTable.TABLE_NAME} WHERE ${CityTable.API_ID} = ${id};")
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityTable.TABLE_NAME, true,
                Pair(CityTable.ID, INTEGER + PRIMARY_KEY),
                Pair(CityTable.NAME, TEXT),
                Pair(CityTable.API_ID, TEXT));

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}