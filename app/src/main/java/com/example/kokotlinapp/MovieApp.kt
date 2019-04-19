package com.example.kokotlinapp

/*IMPORT*/
import android.app.Application
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

//Classe présente dans le manifest
//Elle est appelée avant les autres activités et détruite à la fin
class MoviesApp: Application() {

    companion object {
        lateinit var requetQueue: RequestQueue //Initialisation de la requete avec Volley
    }

    override fun onCreate() {
        super.onCreate()
        requetQueue = Volley.newRequestQueue(this)
    }
}