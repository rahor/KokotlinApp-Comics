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
        // Création d'une méthode statique pour générer et remplir un nouvel Intent
        private val EXTRA_MOVIE = "extra_movie"

        fun createIntent(context: Context, film: Film): Intent {
            val intent = Intent(context, DetailFilmActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, film)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        // Récupération de l'objet movie (transféré dans l'intent - La Mouche (1986) - David Cronenberg)
        val film = intent.getParcelableExtra<Film>(EXTRA_MOVIE)

        // On affiche le titre du film dans l'App Bar
        this.title = film.titre


        Glide
            .with(AfficheDetailImageView)
            .load("http://neopixl.alwaysdata.net/comicvine/thumbs/868515-watchmen_final_poster.jpg")

            // .load(UrlBuilder.afficheUrl(film.afficheURL ?: ""))
            .into(AfficheDetailImageView)

        //Remplir la description du film
        descriptionDetailTextView.text = film.description

    }
}