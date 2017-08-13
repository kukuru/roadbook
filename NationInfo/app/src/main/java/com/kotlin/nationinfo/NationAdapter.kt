package com.kotlin.nationinfo

/**
 * Created by nanjui on 2016. 9. 10..
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.nationinfo.R

data class NationData (
        var resId:Int,
        var name:String,
        var capital:String)

class ViewHolder(view:View) : RecyclerView.ViewHolder(view)
{
    val img_flag : ImageView = view.findViewById(R.id.img_flag) as ImageView
    val txt_name : TextView = view.findViewById(R.id.text_name) as TextView
    val txt_capital : TextView = view.findViewById(R.id.capital) as TextView
}


class NationAdapter(val context:Context,
                    val items:List<NationData>):RecyclerView.Adapter<ViewHolder>()
{
    private val mInflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var onItemClick:View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val mainView : View = mInflater.inflate(R.layout.layout_nation_list_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return ViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img_flag.setImageResource(items[position].resId)
        holder.txt_capital.text = items[position].capital
        holder.txt_name.text = items[position].name
        holder.txt_name.tag = position
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickListener(l:View.OnClickListener)
    {
        onItemClick = l
    }
}
