package com.kotlin.registerusers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.kotlin.registerusers.DB.DBHanlder_Anko
import kotlinx.android.synthetic.main.layout_user_list.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private var mAdapter:UserListAdapter? = null
    var mDBHandler:DBHanlder_Anko = DBHanlder_Anko(this)

    companion object{
        val REQUEST_ADD_USER = 1001
        val UpdateUserData = "update"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set toolbar
        val toolbar:Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val newOne = mDBHandler.getUserAllWithCursor()
        if(newOne?.count != 0) {
            mAdapter = UserListAdapter(this, newOne)
            val listView = findViewById(R.id.user_list) as ListView
            listView.adapter = mAdapter
            (mAdapter as UserListAdapter).setOnItemClick(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter?.cursor?.close()
        mDBHandler.close()
        apply {  }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            REQUEST_ADD_USER->{
                val newOne = mDBHandler.getUserAllWithCursor()
                if(mAdapter==null){
                    mAdapter = UserListAdapter(applicationContext, newOne)
                    val listView = findViewById(R.id.user_list) as ListView
                    listView.adapter = mAdapter
                    (mAdapter as UserListAdapter).setOnItemClick(this)
                }
                mAdapter?.changeCursor(newOne)
                mAdapter?.notifyDataSetInvalidated()
            }
        }
    }

    fun onClickDelete(view:View)
    {
        mDBHandler.deleteUser(view.tag as Long)
        val newOne = mDBHandler.getUserAllWithCursor()
        mAdapter?.changeCursor(newOne)
    }

    override fun onCreateOptionsMenu(menu: Menu):Boolean
    {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu:Menu):Boolean
    {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, SaveUserActivity::class.java)
        intent.putExtra(UpdateUserData, v?.del_item?.tag as Long)
        startActivityForResult(intent, REQUEST_ADD_USER)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.add_user->{
                val intent:Intent = Intent(this, SaveUserActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD_USER)
            }

            R.id.anko-> {
                val layout:Intent = Intent(this, AnkoDSLActivity::class.java)
                startActivity(layout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
