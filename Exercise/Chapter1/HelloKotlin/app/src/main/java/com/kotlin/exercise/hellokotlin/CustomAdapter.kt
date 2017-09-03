package com.kotlin.exercise.hellokotlin

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import java.util.*


/**
 * Created by NanKuru on 2017-09-03.
 */
class CustomAdapter : BaseAdapter {
    private var mContext: Context? = null
    private var mOnClick: View.OnClickListener? = null
    private var mItemArray: ArrayList<String>? = null

    constructor(context: Context, itemArray: ArrayList<String>) {
        mContext = context
        mItemArray = itemArray
    }

    constructor(context: Context, itemArray: ArrayList<String>, listener: View.OnClickListener) {
        mContext = context
        mOnClick = listener
        mItemArray = itemArray
    }
}

class Temp{
    fun sumEvenNumber():Int {
        var result = 0
        (1..50).filter { it % 2 == 0 }.forEach { result += it }
        return result
    }

    private fun setLayoutParam(view: View) {
        (view.layoutParams as LinearLayout.LayoutParams).apply {
            gravity = Gravity.CENTER_VERTICAL
            topMargin = 50
            leftMargin = 50
        }
    }
}