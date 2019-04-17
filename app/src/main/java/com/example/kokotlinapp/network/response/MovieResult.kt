package com.example.kokotlinapp.network.response

import com.example.kokotlinapp.model.Film
import com.fasterxml.jackson.annotation.JsonProperty

class MovieResult {


    var page = 1

    //JSON total_result kotlin = totalResult
    //Annotation permettant d'assurer la correspondance entre la clé dans le JSON et la propriété ds notre classe
    @JsonProperty(value = "total_results")
    var totalResult = 0

    @JsonProperty(value = "total_pages")
    var totalPages = 0

    lateinit var results: Array<Film>
}