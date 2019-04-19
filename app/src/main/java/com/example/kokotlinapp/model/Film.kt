package com.example.kokotlinapp.model

/*IMPORT*/
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)

//Classe de Film pour créer des objets de type film
class Film() : Parcelable {
    @JsonProperty(value = "name")
    lateinit var titre: String

    @JsonProperty(value = "image")
    var afficheURL: String? = null

    @JsonProperty(value = "description")
    var description: String? = null

    //Constructor pour construire des objets de type film
    constructor(titre: String, description: String, affiche: String) : this() {
        this.titre = titre
        this.description = description
        this.afficheURL = affiche
    }


    //Constructeur et fonctions pour parcel le json et créer des objets de type film
    //Ces fonctions sont inutiles car on utilise le constructeur principal
    constructor(parcel: Parcel) : this() {
        // lecture dans le même ordre que la méthode writeToParcel
        titre = parcel.readString()
        afficheURL = parcel.readString()
        description = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // écriture dans le même ordre que le constructeur constructor(parcel: Parcel)
        parcel.writeString(titre)
        parcel.writeString(afficheURL)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }

}