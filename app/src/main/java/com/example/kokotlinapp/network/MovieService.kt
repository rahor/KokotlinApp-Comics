package com.example.kokotlinapp.network


import android.util.Log

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.example.kokotlinapp.MoviesApp
import com.example.kokotlinapp.model.Film
import org.json.JSONArray
import org.json.JSONObject

/*

class MovieService {
  companion object {
      fun searchMovies(query: String, success: (film: Array<Film>) -> Unit, failure: (error: VolleyError?) ->Unit ){
          //lance la requete
          val url  = UrlBuilder.searchMovieURL()
       val request = BaseRequest.Builder<MovieResult>(Request.Method.GET,url,MovieResult::class.java)
              .listener(object: RequestListener<MovieResult> {
                  override fun onSuccess(
                      request: Request<MovieResult>,
                      response: NetworkResponse,
                      movieResult: MovieResult? //objet potentiellement nul
                  ) {
                      val movies = movieResult?.results ?: emptyArray()

                      //On renvoit les informations (a.k.a le tab de movies)
                      success(movies)
                  }

                  override fun onFailure(
                      request: Request<MovieResult>,
                      response: NetworkResponse?,
                      error: VolleyError?
                  ) {
                      failure(error)
                  }

              }).build() //crée un requête
          //Envoi de la requete
          MovieApp.requetQueue.add(request)
        }
    }

} */


    class MovieService {
    companion object {
        fun searchMovies(success: (film: Array<Film>) -> Unit, failure: (error: VolleyError?) ->Unit ){
            //on récupère l'URL
            val url  = UrlBuilder.searchMovieURL()
            Log.d("url",url)
            Log.d("ok","ça marche")


            val requete = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    Log.d("hah",response.toString())

                },
                Response.ErrorListener { error ->
                }
            )

// Access the RequestQueue through your singleton class.
            MoviesApp.requetQueue.add(requete)



}


        fun getMovies(success: (movies: MutableList<Film>) -> Unit, failure: (error: VolleyError?) ->Unit  ) {
            // Instantiate the RequestQueue.

            val url: String ="https://comicvine.gamespot.com/api/movies/?api_key=8a143eaac23bf143fe1f49d738c4920bfeda741b&format=json"

            // Request a string response from the provided URL.
            val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    Log.d( "jetestdestrucs","response")
                    var strResp = response.toString()
                    val jsonObj: JSONObject = JSONObject(strResp)

                    //Récupération des resultats de la requête
                    val jsonArray: JSONArray = jsonObj.getJSONArray("results")
                    var titre: String = ""
                    var affiche: String = ""
                    var description : String = ""
                    val listeFilms = mutableListOf<Film>()
                    var jsonInner: JSONObject = jsonArray.getJSONObject(0)
                    for (i in 0 until  jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        titre = "\n" + jsonInner.get("name")

                        affiche = jsonInner.getJSONObject("image").getString("small_url")
                        description = "\n" + jsonInner.get("description")
                        Log.d( "jetestdestrucs","Le titre: $titre ")

                        listeFilms.add(Film(titre,description,affiche))

                    }

                    /*val film1 = Film(titre,description,affiche)
                    val filmtest = Film("fuck fuck fuck ", " fuck")
                    listeFilms.add(film1)
                    listeFilms.add(filmtest)
                    */

                    success(listeFilms)
                    // success(JsonToMovies(jsonArray))
                },
                Response.ErrorListener { Log.d( "ERROR","Impossible de trouver les films") })


            MoviesApp.requetQueue.add(stringReq)
        }

        fun JsonToMovies(jsonArray: JSONArray ):MutableList<Film>{
            val listeFilms = mutableListOf<Film>()
            var titre: String = ""
            var affiche: String = ""
            var description : String = ""

            for (i in 0 until jsonArray.length()) {
                val jsonInner: JSONObject = jsonArray.getJSONObject(i)

                affiche = jsonInner.getJSONObject("image").getString("small_url")
                description = "\n" + jsonInner.get("description")
                titre = "\n" + jsonInner.get("name")

                val listeFilm = Film(titre,description,affiche)

                       }
            return listeFilms
        }


    }}