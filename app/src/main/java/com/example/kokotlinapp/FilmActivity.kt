package com.example.kokotlinapp

/*IMPORT*/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kokotlinapp.item.FilmItem
import com.example.kokotlinapp.network.MovieService
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_film.*


//Bonjour, je suis un peu peintre =). J'espère que mon application va vous plaire. Bonne journée ou soirée!
class FilmActivity : AppCompatActivity() {

    private lateinit var filmAdapter: FastItemAdapter<FilmItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        //Création de l'adaptateur pour l'utiliser dans la recycle view
        filmAdapter = FastItemAdapter<FilmItem>()
        filmRecyclerView.adapter = filmAdapter

        //On utilise un GridLayoutManager pour disposer les films en grille
        filmRecyclerView.layoutManager = GridLayoutManager(this, 2)
        /*Pour l'affichage en ligne
        filmRecyclerView.layoutManager = LinearLayoutManager(this)*/

        //Creation d'un indent pour passer à l'écran du détail du film au click sur le film
        filmAdapter.withOnClickListener { view, adapter, item, position ->
            val film = item.film

            //On donne le film en paramètre pour récupérer les données comme l'affiche ou la description (utilisation du companion object)
            val intent = DetailFilmActivity.createIntent(this, film)

            //On démarre l'activité des détails de films
            startActivity(intent)
            true
        }


        //Appel de la fonction pour afficher les films
        afficherFilms()


        //Si on veut rechercher
     /*   searchEditText.addTextChangedListener { editable ->
            val query = editable.toString()

            afficherFilm(query)
       }*/

        }

    //Fonction pour afficher les films
    fun afficherFilms(){
    //Il faudrait rajouter un paramètre de type string pour récupérer ce que rentre l'utilisateur (exemple: QUERY)


        MovieService.requeteFilms({resultMovies ->
            //filmAdapter.clear() //Pour effacer les films dans l'adaptater en cas de recherche

            //Pour chaque film dans la liste de films récupérée on affiche le film
            for (movie in resultMovies) { filmAdapter.add(FilmItem(movie)) }


        //Je n'ai pas réussi à implémenter la recherche à cause de l'import qui ne fonctionne pas
                // filter va boucler sur tous les films
            /*    val filteredMovies = resultMovies.filter {
                    // On conserve le film si il y a une correspondance
                    // entre le texte de recherche et le titre du film

                    // Ici on retourne le test (Est-ce que le titre du film contient la recherche ?)
                    it.titre.toLowerCase().contains(QUERY.toLowerCase())
                }

                // On va effacer la liste (RecyclerView)
                filmAdapter.clear()

                // On affiche les nouveaux résultats (rafraichissement)

                for(movie in filteredMovies) {
                    filmAdapter.add(FilmItem(movie))
                }*/

        }, {
            Log.e("Volly Error", "Erreur") //Gestion des erreurs
            })
    }




}
