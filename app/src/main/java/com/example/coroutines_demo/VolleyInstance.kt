package com.example.coroutines_demo

import android.content.Context
import com.android.volley.RequestQueue

import com.android.volley.toolbox.Volley

class VolleyInstance private constructor(context: Context) {
    val requestQueue: RequestQueue

    companion object {
        private var mInstance: VolleyInstance? = null
        @Synchronized
        fun getmInstance(context: Context): VolleyInstance? {
            if (mInstance == null) {
                mInstance = VolleyInstance(context)
            }
            return mInstance
        }
    }

    init {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext())
    }
}