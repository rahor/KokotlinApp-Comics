package com.example.kokotlinapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.kokotlinapp.model.Film
import com.example.kokotlinapp.network.UrlBuilder
import kotlinx.android.synthetic.main.activity_detail_film.*
//import kotlinx.android.synthetic.main.cellule_film.view.*



class DetailFilmActivity : AppCompatActivity() {
    companion object {
        private val INTENT_MOVIE = "intent_movie"

        fun createIntent(context: Context, film: Film): Intent {
            val intent = Intent(context, DetailFilmActivity::class.java)
            intent.putExtra(INTENT_MOVIE, film)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        // Récupération de l'objet movie transféré dans l'intent
        val film = intent.getParcelableExtra<Film>(INTENT_MOVIE)

        // On affiche le titre du film dans l'App Bar
        this.title = film.titre


        Glide
            .with(AfficheDetailImageView)
            .load(film.afficheURL)

            // .load(UrlBuilder.afficheUrl(film.afficheURL ?: ""))
            .into(AfficheDetailImageView)

        //Remplir la description du film
        descriptionDetailTextView.text = film.description

    }
}