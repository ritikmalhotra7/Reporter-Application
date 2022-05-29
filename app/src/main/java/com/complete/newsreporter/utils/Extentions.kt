package com.complete.newsreporter.utils

import android.content.Context

fun Context.save(key:String,value:String){
    this.getSharedPreferences(Constants.SHARED, Context.MODE_PRIVATE)?.edit()?.apply{
        putString(key,value)
        apply()
    }
}

fun Context.read(key:String):String{
    return this.getSharedPreferences(Constants.SHARED,Context.MODE_PRIVATE).getString(key,"in")?:"in"
}
fun Context.readPos(key:String):Int{
    return this.getSharedPreferences(Constants.SHARED,Context.MODE_PRIVATE).getString(key,"0")!!.toInt()
}