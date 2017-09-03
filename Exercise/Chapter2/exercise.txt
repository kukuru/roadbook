## 1번
~~~
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
~~~

## 2번

~~~
fun sumEvenNumber():Int{
        var result = 0
        (1..50).filter { it % 2 == 0 }.forEach { result += it }
        return result
    }
~~~

## 3번

~~~
fun setLayoutParam(view: View) {
        when(view){
            is LinearLayout ->{
                var param : LinearLayout.LayoutParams = view.layoutParams as LinearLayout.LayoutParams
                param.gravity = Gravity.BOTTOM
                view.layoutParams =  param
            }
            is RelativeLayout->{
                var param : RelativeLayout.LayoutParams = view.layoutParams as RelativeLayout.LayoutParams
                param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                view.layoutParams = param
            }
        }
    }
~~~

## 4번

~~~
private fun setLayoutParam(view: View) {
        view.layoutParams = (view.layoutParams as LinearLayout.LayoutParams).apply {
            gravity = Gravity.CENTER_VERTICAL
            topMargin = 50
            leftMargin = 50
        }
    }
~~~





