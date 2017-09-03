package com.kotlin.registerusers.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by nanjui on 2016. 10. 29..
 */
data class UserInfo(val id:Long = 0, val name:String = "No Name",
                    val age:String = "0",
                    val TelNum:String = "No TelNum",
                    val pic_path:String)

enum class UserData(val index:Int){
    _id(0),
    Name(1),
    Age(2),
    TelNum(3),
    PicPath(4)
}

class DBHandler: SQLiteOpenHelper{
    constructor(context:Context):super(context, DB_Name, null, DB_Version)

    companion object {
        val DB_Name = "user.db"
        val DB_Version = 1;
    }

    val TABLE_NAME = "user"
    val ID = "_id"
    val NAME = "name"
    val AGE = "age"
    val TELNUM = "telnum"
    val PIC_PATH = "pic_path"

    val TALBE_CREATE = "CREATE TABLE if not exists " + TABLE_NAME + " (" +
            "${ID} integer PRIMARY KEY ,t, ${NAME} text," +
            "${AGE} text, ${TELNUM} text, ${PIC_PATH} text"+  ")"

    fun getUserAllWithCursor():Cursor{
        val cursor = readableDatabase.query(TABLE_NAME, arrayOf(ID, NAME, AGE, TELNUM,PIC_PATH), null, null, null, null, null)
        return cursor
    }

    fun addUser(user:UserInfo)
    {
        var info = ContentValues()
        info.put(NAME, user.name)
        info.put(AGE, user.age)
        info.put(TELNUM, user.TelNum)
        info.put(PIC_PATH, user.pic_path)
        writableDatabase.insert(TABLE_NAME, null, info)
        writableDatabase.close()
    }

    fun deleteUser(id:Long)
    {
        writableDatabase.execSQL("DELETE FROM ${TABLE_NAME} WHERE ${ID} = ${id};")
        writableDatabase.close()
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TALBE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}