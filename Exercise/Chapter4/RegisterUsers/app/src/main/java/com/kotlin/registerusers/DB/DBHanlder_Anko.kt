package com.kotlin.registerusers.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

/**
 * Created by nanjui on 2016. 11. 20..
 */


class Singleton {
    companion object {
        val instance: Singleton by lazy(LazyThreadSafetyMode.PUBLICATION) { Singleton() }
    }
}
class DBHanlder_Anko(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_Version) {
    companion object {
        val DB_Name = "user.db"
        val DB_Version = 1;
    }

    object UserTable
    {
        val TABLE_NAME = "user"
        val ID = "_id"
        val NAME = "name"
        val AGE = "age"
        val TELNUM = "telnum"
        val PIC_PATH = "pic_path"
    }

    fun getUserAllWithCursor(): Cursor {
        return readableDatabase.query(UserTable.TABLE_NAME,
                    arrayOf(UserTable.ID, UserTable.NAME, UserTable.AGE, UserTable.TELNUM, UserTable.PIC_PATH),
                    null, null, null, null, null)
    }

    fun getUserWithId(id:Long) : UserInfo{
        val cursor = readableDatabase.query(UserTable.TABLE_NAME,
                arrayOf(UserTable.ID, UserTable.NAME, UserTable.AGE, UserTable.TELNUM, UserTable.PIC_PATH),
                UserTable.ID + "=?",
                arrayOf(id.toString()), null, null, null)
        cursor.moveToFirst()
        return UserInfo(cursor.getLong(0),cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4))
    }

    fun updateUser(user:UserInfo){
        var info = ContentValues()
        info.put(UserTable.NAME, user.name)
        info.put(UserTable.AGE, user.age)
        info.put(UserTable.TELNUM, user.TelNum)
        info.put(UserTable.PIC_PATH, user.pic_path)

        writableDatabase.use{
            writableDatabase.update(UserTable.TABLE_NAME, info, "_id=?", arrayOf(user.id.toString()))
        }
    }

    fun addUser(user:UserInfo)
    {
        var info = ContentValues()
        info.put(UserTable.NAME, user.name)
        info.put(UserTable.AGE, user.age)
        info.put(UserTable.TELNUM, user.TelNum)
        info.put(UserTable.PIC_PATH, user.pic_path)

        writableDatabase.use {
            writableDatabase.insert(UserTable.TABLE_NAME, null, info)
        }
    }

    fun deleteUser(id:Long)
    {
        writableDatabase.use {
            writableDatabase.execSQL("DELETE FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.ID} = ${id};")
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(UserTable.TABLE_NAME, true,
                Pair(UserTable.ID, INTEGER + PRIMARY_KEY),
                Pair(UserTable.NAME, TEXT),
                Pair(UserTable.AGE, TEXT),
                Pair(UserTable.TELNUM, TEXT),
                Pair(UserTable.PIC_PATH, TEXT));
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}