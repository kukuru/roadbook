package com.kotlin.weathercast.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kotlin.weathercast.data.CityData
import org.jetbrains.anko.db.*
import java.util.*

/**
 * Created by NanKuru on 2017-09-07.
 */

//object 키워드를 이용하여 클래스를 만들면 DBManager를 사용하기 전까지는 생성되지 않습니다.
object DBManager{
    private var mDbHandler:DBHandlerAnkoSingle?=  null

    fun init(context:Context){
        if(mDbHandler == null){
            mDbHandler = DBHandlerAnkoSingle(context)
        }
    }

    fun getCityDataAll(): ArrayList<CityData> {
        val data = ArrayList<CityData>()
        val cursor = mDbHandler?.writableDatabase?.query(DBHanlderAnko.TABLE_NAME,
                arrayOf(DBHanlderAnko.ID, DBHanlderAnko.NAME, DBHanlderAnko.API_ID),
                null, null, null, null, null)

        //엘비스 표현을 이용하여 null 경우는 빈 ArrayList를 넘겨줍니다.
        cursor?:return data
        if(cursor.count == 0)
            return data

        cursor.moveToFirst()
        do
        {
            val city: CityData = CityData(cursor.getString(2), cursor.getString(1))
            data.add(city)
        }while(cursor.moveToNext()?:false)
        return data
    }

    fun saveCity(city: CityData)
    {
        var info = ContentValues()
        info.put(DBHanlderAnko.NAME, city.name)
        info.put(DBHanlderAnko.API_ID, city._id)

        mDbHandler?.writableDatabase.use {
            mDbHandler?.writableDatabase?.insert(DBHanlderAnko.TABLE_NAME, null, info)
        }
    }

    fun deleteCity(id:String)
    {
        mDbHandler?.writableDatabase.use {
            mDbHandler?.writableDatabase?.execSQL("DELETE FROM ${DBHanlderAnko.TABLE_NAME} WHERE ${DBHanlderAnko.API_ID} = ${id};")
        }
    }
}

class DBHandlerAnkoSingle(context: Context) : SQLiteOpenHelper(context,DB_Name, null, DB_Version){
    companion object CityTable{
        val DB_Name = "user.db"
        val DB_Version = 1;
        val TABLE_NAME = "city"
        val ID = "_id"
        val NAME = "name"
        val API_ID = "api_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(DBHanlderAnko.TABLE_NAME, true,
                Pair(DBHanlderAnko.ID, INTEGER + PRIMARY_KEY),
                Pair(DBHanlderAnko.NAME, TEXT),
                Pair(DBHanlderAnko.API_ID, TEXT));
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}