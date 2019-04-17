package com.example.kokotlinapp.network

class UrlBuilder {

    //Pour créer les méthodes statiques utilisables partout
    companion object {

        //clé de l'api
        val apiKey = "8a143eaac23bf143fe1f49d738c4920bfeda741b"


        //On recupere l'URL du film
        fun searchMovieURL(): String{
        // fun searchMovieURL(query: String): String{
          //  return "https://comicvine.gamespot.com/api/movies/?api_key=$apiKey&format=json"

            return "https://api.themoviedb.org/3/search/movie?api_key=c1ac741d5dd740f9861e794c5363b0c2&query=titanic"
            //&filter=name:$query
        }


        //Pour récuperer l'image du film
        fun afficheUrl(afficheURL: String) : String {
            return "http://neopixl.alwaysdata.net/comicvine/thumbs/$afficheURL"
        }
    }


}