package com.example.kokotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kokotlinapp.item.FilmItem
import com.example.kokotlinapp.model.Film
import androidx.recyclerview.widget.RecyclerView
import com.example.kokotlinapp.network.MovieService

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_film.*

class FilmActivity : AppCompatActivity() {

    // Créer un espace de stockage pour les films
    val listFilm = mutableListOf<Film>() // liste de films

    private lateinit var filmAdapter: FastItemAdapter<FilmItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        listFilm.add(Film("Watchmen","Hommes-Montres"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))
        listFilm.add(Film("Bla","okokok"))


        filmAdapter = FastItemAdapter<FilmItem>()

       /* for(movie in listFilm) {
            // On ajoute 1 MovieItem par Movie dans l'adapter
            filmAdapter.add(FilmItem(movie))
        }*/

        filmRecyclerView.adapter = filmAdapter

       // filmRecyclerView.layoutManager = GridLayoutManager(this, 2)
        //On utilise un LayoutManager
        filmRecyclerView.layoutManager = LinearLayoutManager(this)

        filmRecyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))


        filmAdapter.withOnClickListener { view, adapter, item, position ->
            val film = item.film
            val intent = DetailFilmActivity.createIntent(this, film)

            //On démarre l'activité des détails de films
            startActivity(intent)

            true
        }

        getFilms()
           // findMovies()

    }

    fun getFilms(){

            filmAdapter.clear()
            MovieService.getMovies({resultMovies ->
                filmAdapter.clear()

                for (movie in resultMovies) {
                    filmAdapter.add(FilmItem(movie))
                }
            }, {
            })
    }



    fun findMovies() {
        MovieService.searchMovies({resultMovies ->
            filmAdapter.clear()

            for (movie in resultMovies) {
                filmAdapter.add(FilmItem(movie))
            }
        }, {
        })
    }

}
