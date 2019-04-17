package com.example.kokotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Passage au deuxième écran
        accueilBtn.setOnClickListener {
            val intentSecondActivity = Intent(this, FilmActivity::class.java)
            startActivity(intentSecondActivity)
        }

    }
}
