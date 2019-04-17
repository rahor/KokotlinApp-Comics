package com.example.kokotlinapp

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MoviesApp: Application() {

    companion object {
        lateinit var requetQueue: RequestQueue //Vient de volley
    }

    override fun onCreate() {
        super.onCreate()

        requetQueue = Volley.newRequestQueue(this)
    }
}