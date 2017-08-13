package com.test.weathercast.db

/**
 * Created by NanKuru on 2017-08-10.
 */
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.test.weathercast.data.CityData
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable
import java.util.*
object DBManager{
    var mDB:DBHanlderAnko? = null
    fun getInstance(context: Context){
        mDB = DBHanlderAnko(context)
    }

    fun getCityDataAll(): ArrayList<CityData> {
        val data = ArrayList<CityData>()

        val cursor = mDB?.readableDatabase?.query(DBHanlderAnko.TABLE_NAME,
                arrayOf(DBHanlderAnko.ID, DBHanlderAnko.NAME, DBHanlderAnko.API_ID),
                null, null, null, null, null)
        if(cursor?.count == 0)
            return data

        cursor?.moveToFirst()
        do
        {
            val city: CityData = CityData(cursor.getString(2), cursor.getString(1))
            data.add(city)
        }while(cursor.moveToNext())
        return data
    }

    fun saveCity(city: CityData)
    {
        mDB?.writableDatabase.use {
            it?.insert(DBHanlderAnko.TABLE_NAME, null, ContentValues().
                    apply {
                        put(DBHanlderAnko.NAME, city.name)
                        put(DBHanlderAnko.API_ID, city._id)
                    })
        }
    }

    fun deleteCity(id:String)
    {
        mDB?.writableDatabase.use {
            it?.execSQL(
                    "DELETE FROM ${DBHanlderAnko.TABLE_NAME} " +
                            "WHERE ${DBHanlderAnko.API_ID} = ${id};")
        }
    }
}

class DBHanlderAnko(context: Context) : SQLiteOpenHelper(context, DB_Name, null,
        DB_Version) {
    companion object CityTable{
        val DB_Name = "user.db"
        val DB_Version = 1;
        val TABLE_NAME = "city"
        val ID = "_id"
        val NAME = "name"
        val API_ID = "api_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityTable.TABLE_NAME, true,
                Pair(CityTable.ID, INTEGER + PRIMARY_KEY),
                Pair(CityTable.NAME, TEXT),
                Pair(CityTable.API_ID, TEXT))
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}