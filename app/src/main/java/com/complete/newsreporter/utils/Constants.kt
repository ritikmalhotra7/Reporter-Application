package com.complete.newsreporter.utils

import android.app.Activity
import android.content.Context

class Constants {
    companion object{
        const val API_KEY = "d1bd835912d245aaa3bbe7a8af36fdd7"
        const val BASE_URL = "https://newsapi.org"
        const val QUERY_SIZE = 20
        const val SHARED = "shared"

        const val ABP_NEWS = "https://www.youtube.com/watch?v=nyd-xznCpJc&ab_channel=ABPNEWS"
        const val NDTV_NEWS = "https://www.youtube.com/watch?v=WB-y7_ymPJ4&ab_channel=NDTV"
        const val INDIA_TODAY_NEWS = "https://www.indiatoday.in/livetv"
        const val AAJ_TAK = "https://www.indiatoday.in/aajtak-livetv"
        const val GNT_NEWS = "https://www.indiatoday.in/gnt-livetv"

        var showNotification = true

        fun saveToSharedPrefrences(key:String,value:String,ctx:Activity){
            ctx.getSharedPreferences(SHARED, Context.MODE_PRIVATE)?.edit()?.apply{
                putString(key,value)
                apply()
            }
        }
        fun getFromSharedPrefrences(key:String,ctx:Activity){
            ctx.getSharedPreferences(SHARED, Context.MODE_PRIVATE)?.getString(key,"")
        }
    }

}