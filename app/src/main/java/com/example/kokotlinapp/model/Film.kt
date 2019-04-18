package com.example.kokotlinapp.model
import android.accounts.AuthenticatorDescription
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)

class Film() : Parcelable {
    @JsonProperty(value = "name")
    lateinit var titre: String

    @JsonProperty(value = "image")
    var afficheURL: String? = null

    @JsonProperty(value = "description")
    var description: String? = null

    constructor(titre: String, description: String, affiche: String) : this() {
        // On stocke dans la propriété interne, la valeur passé en paramètre du constructeur
        // this.title fait référence à la variable en interne (celle définie en haut)
        // title fait référence au paramètre
        this.titre = titre
        this.description = description
        this.afficheURL = affiche
    }

    constructor(titre: String, description: String) : this() {
        // On stocke dans la propriété interne, la valeur passé en paramètre du constructeur
        // this.title fait référence à la variable en interne (celle définie en haut)
        // title fait référence au paramètre
        this.titre = titre
        this.description = description
    }


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