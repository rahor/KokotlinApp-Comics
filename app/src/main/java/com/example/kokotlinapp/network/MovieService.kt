package com.example.kokotlinapp.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.VolleyError
import com.bumptech.glide.request.RequestListener
import com.example.kokotlinapp.MoviesApp
//import com.example.kokotlinapp.MovieApp
import com.example.kokotlinapp.model.Film
import com.example.kokotlinapp.network.response.MovieResult

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
            MoviesApp.requetQueue.add(request)
        }
    }

}