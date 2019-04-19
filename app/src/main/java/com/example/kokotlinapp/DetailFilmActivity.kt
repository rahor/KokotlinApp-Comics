package com.example.kokotlinapp

/*IMPORT*/
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.kokotlinapp.model.Film
import kotlinx.android.synthetic.main.activity_detail_film.*

//Activité des détails du film
class DetailFilmActivity : AppCompatActivity() {

    //On crée un companion objec pour passer de l'activité principale à la deuxième
    //Sera utilisé au clic sur un film
    companion object {
        private val INTENT_MOVIE = "intent_movie"

        //Création de l'intent pour récupérer les films et les afficher dans l'activité de détails
        fun createIntent(context: Context, film: Film): Intent {
            val intent = Intent(context, DetailFilmActivity::class.java)
            //On passe un film que l'on va récupérer
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

        //On affiche le titre du film
        titreDetailTextView.text = film.titre

        //Chargement de l'image avec l'url dans l'image de l'activité
        Glide
            .with(AfficheDetailImageView)
            .load(film.afficheURL)
            .thumbnail(0.1f)
            .into(AfficheDetailImageView)

        //Remplir la description du film
        descriptionDetailTextView.text = film.description

        //On fini l'activité pour retourner à la dernière
        retourBoutton.setOnClickListener {
            this.finish()
        }
    }
}