## 1번
1. 먼저 app gradle 파일 수정합니다.
~~~
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

이하 생략
~~~

2. sync now를 통해서 gradle 파일 동기화 시켜줍니다.

3. 아래와 같이 변경할 수 있습니다.
~~~
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
		// 생략
		nation_list.layoutManager = LinearLayoutManager(this)
		nation_list.adapter = adapter
		
		//이하 생략
}
~~~

## 2번
함수를 구성하는 방법은 다양합니다.
저자는 짝수인지 아닌지를 이용하여 함수를 구성하였습니다.
~~~
fun isEven():Boolean{
        var result = 0
        for(i in 0..50){
            result += i
        }
        when(result%2){
            0-> return true
        }
        return false
    
~~~

