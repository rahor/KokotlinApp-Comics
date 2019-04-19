package com.example.kokotlinapp.network

//Pour appeler les URLs
class UrlBuilder {

    //Pour créer les méthodes statiques utilisables partout
    companion object {

        //clé de l'api comics
        val apiKey = "8a143eaac23bf143fe1f49d738c4920bfeda741b" //val apiKey = "YOUR API KEY"


        //On recupere l'URL des films
        fun UrlFilms(): String {
            return "https://comicvine.gamespot.com/api/movies/?api_key=$apiKey&format=json"
        }

    }

}