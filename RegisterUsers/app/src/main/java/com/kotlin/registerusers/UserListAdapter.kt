package com.kotlin.registerusers

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.registerusers.DB.UserData

data class ViewHolder(val pic:ImageView,
                      val name:TextView,
                      val tel:TextView,
                      val del:ImageView)

class UserListAdapter(context:Context, cursor:Cursor?)
        : CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER)
{
    val mCtx = context

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mainView = inflater.inflate(R.layout.layout_user_list, parent,false)
        var holder:ViewHolder = ViewHolder(mainView.findViewById(R.id.profile) as ImageView,
                mainView.findViewById(R.id.name) as TextView,
                mainView.findViewById(R.id.tel_num) as TextView,
                mainView.findViewById(R.id.del_item) as ImageView)
        mainView.tag = holder
        return mainView
    }

    override fun bindView(convertView: View, context: Context, cursor: Cursor) {
        var name_title = mCtx.resources.getString(R.string.user_title)
        val holder = convertView.tag as ViewHolder

        holder.name.text = String.format(name_title, cursor.getString(UserData.Name.index), cursor.getInt(UserData.Age.index))
        holder.tel.text = cursor.getString(UserData.TelNum.index)
        val picture:Drawable = getPicture(cursor.getString(UserData.PicPath.index))?:context.getDrawable(android.R.drawable.ic_menu_gallery)
        picture.let {
            holder.pic.background = picture
        }

        //save cursor id
        holder.del.tag = cursor.getLong(0)
    }

    private fun getPicture(path:String): Drawable?
    {
        val img_id = path.toLong()
        if(img_id == 0L) return null

        var bitmap:Bitmap? = MediaStore.Images.Thumbnails.getThumbnail(mCtx.contentResolver,img_id,
                MediaStore.Images.Thumbnails.MICRO_KIND,null)

        bitmap?:return null
        return BitmapDrawable(mCtx.resources, bitmap)
    }
}