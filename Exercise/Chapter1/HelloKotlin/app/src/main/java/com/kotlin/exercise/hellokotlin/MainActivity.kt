package com.kotlin.exercise.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun isEven():Boolean{
        var result = 0
        for(i in 0..50){
            result += i
        }
        when(result%2){
            0-> return true
        }
        return false
    }
}
