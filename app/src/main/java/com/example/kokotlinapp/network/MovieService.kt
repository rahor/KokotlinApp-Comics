package com.example.kokotlinapp.network

/*IMPORT*/
import android.text.Html
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.kokotlinapp.MoviesApp
import com.example.kokotlinapp.model.Film
import org.json.JSONArray
import org.json.JSONObject

    class MovieService {
    companion object {
        fun requeteFilms(success: (movies: MutableList<Film>) -> Unit, failure: (error: VolleyError?) ->Unit  ) {

            //On récupère l'URL de l'API grâce à notre UrlBuilder
            val url  = UrlBuilder.UrlFilms()

            //On demande une réponse en String de notre requête
            val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { reponse ->
                    var strResp = reponse.toString()
                    val jsonObj: JSONObject = JSONObject(strResp)

                    //Récupération des resultats de la requête
                    val jsonArray: JSONArray = jsonObj.getJSONArray("results")

                    //Déclaration des éléments à récupérer
                    var titre: String = ""
                    var affiche: String = ""
                    var description : String = ""

                    //On crée une liste de films que l'on va renvoyer dans le success
                    val listeFilms = mutableListOf<Film>()

                    var jsonInner: JSONObject = jsonArray.getJSONObject(0)

                    //On parcourt tous les films et on récupère les informations qui nous intéressent
                    for (i in 0 until  jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        titre = "\n" + jsonInner.get("name")

                        affiche = jsonInner.getJSONObject("image").getString("small_url")
                        description = "\n" + jsonInner.get("description")

                        //on supprime les balises html
                        description =  Html.fromHtml(Html.fromHtml(description).toString()).toString()

                        //Gestion de quelques erreurs : S'il n'y a pas de description on affiche un texte alternatif
                        if ((jsonInner.get("description").toString() == "null" )|| jsonInner.get("description").toString() == "<br />")description="Film sûrement très bien mais pas de description pour le moment."

                        //On ajoute le film dans la liste de film à renvoyer
                        listeFilms.add(Film(titre,description,affiche))
                    }

                    //On renvoi la liste de films pour pouvoir les afficher
                    success(listeFilms)
                },
                //Gestion des erreurs
                Response.ErrorListener { Log.d( "ERROR","Impossible de trouver les films") })

            //On ajoute la requete
            MoviesApp.requetQueue.add(stringReq)
        }
    }
    }