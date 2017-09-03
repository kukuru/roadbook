## 1번

companion object와 object는 행동은 같지만 객체가 생성되는 시점이 다르다는 점에서 차이가 있습니다. 
Object는 객체에 접근할 때, companion object 키워드는 클래스가 로드되는 시점에 객체가 생성되지요. 
자바와 비교해본다 면 companion object가 자바의 static 키워드와 좀더 비슷하다 할 수 있습니다.

## 2번

~~~
highRowTemp.text = "${data.current.main.temp_min} \\u2103 / ${data.
                current.main.temp_max} \\u2103"
~~~

## 3번

~~~
fun loadData(){
        thread {
            val API_KEY : String = "2677d0da8acab2b1d91d54942d6914c8"
            val Current_URL : String = "http://api.openweathermap.org/data/2.5/weather?id="
            val Forcast_URL : String = "http://api.openweathermap.org/data/2.5/forecast?id="
            val ICON_URL : String = "http://openweathermap.org/img/w/"
            val city_weather = ArrayList<WeatherForecast>()
            mCityArray.forEach {
                val cur_url = Current_URL+it._id+"&units=metric&APPID=$API_KEY"
                val readData = URL(cur_url).readText()
                val current: DayData = Gson().fromJson(readData, DayData::class.java)

                current.cityName = it.name
                current.api_id = it._id
                val fore_url = Forcast_URL+it._id+"&units=metric&APPID=$API_KEY"
                val url = URL(fore_url)
                val inputstream = InputStreamReader(url.openStream())
                val week: WeekData = Gson().fromJson(inputstream, WeekData::class.java)
                val forcast: WeatherForecast = WeatherForecast(current, week, ICON_URL)
                city_weather.add(forcast)
            }

            runOnUiThread {
                adapter?:let{
                    adapter = WeatherListViewAdapter(applicationContext, city_weather)
                    adapter?.setDeleteClickListener(){
                        view -> val db = DBHandlerAnko(applicationContext)
                        db.deleteCity(view.tag as String)
                        adapter?.removeData(view.tag as String)
                    }
                    weatherList.adapter = adapter
                    weatherList.layoutManager = LinearLayoutManager(applicationContext)
                }
                mWeatherData?.addAll(city_weather)
                adapter?.updateData(city_weather)
            }
        }
    }
~~~

## 4번

~~~
internal interface NetworkResultCallback {
        fun onResultCallback(result: Int, data: ArrayList<String>)
    }

    inner class Test {
        internal fun setNetworkResultCallback(callback: (Int, ArrayList<String>)->Unit ) {
        }
    }
~~~
