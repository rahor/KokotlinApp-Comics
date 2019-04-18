package com.example.kokotlinapp.item

import android.view.View
import com.bumptech.glide.Glide
import com.example.kokotlinapp.R
import com.example.kokotlinapp.model.Film
import com.example.kokotlinapp.network.UrlBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.cellule_film.view.*

class FilmItem (val film: Film) : AbstractItem<FilmItem, FilmItem.FilmViewHolder>() {

    // Retourne l'id de l'item
    override fun getType(): Int {
        // On retourne un id unique en rapport avec la cellule
        // Par exemple l'id d'un label à l'intérieur de la cellule
        return R.id.cellTitreTextView
    }

    // Méthode appelée un nombre limité de fois
    // A chaque fois qu'on aura besoin d'une nouvelle cellule (View)
    // Donc par exemple environ 10 fois
    override fun getViewHolder(v: View): FilmViewHolder {
        // On appelle le constructeur MovieViewHolder en lui passant la vue fournie
        // pour retourner un nouvel objet de type MovieViewHolder
        return FilmViewHolder(v)
    }

    // Méthode appelée pour récupérer le layout
    override fun getLayoutRes(): Int {
        return R.layout.cellule_film // = Int -> lien vers le fichier layout dans les ressources
    }

    // Cette classe va correspondre à la cellule graphique (View)
    // On aura potentiellement un nombre limité de ViewHolder créés (ex : 10 maximum)
    class FilmViewHolder(itemView: View) : FastAdapter.ViewHolder<FilmItem>(itemView) {

        // Pour nettoyer / recycler la cellule
        override fun unbindView(item: FilmItem) {
            itemView.cellTitreTextView.text = null
            // itemView.movieReleaseDateTextView.text = null
        }

        // Pour mettre à jour l'affichage de la cellule
        override fun bindView(item: FilmItem, payloads: MutableList<Any>) {
            val film = item.film

            // On va récupérer chacune des informations de notre movie
            // et on les affiche dans les composants de la cellule (label, …)

            itemView.cellTitreTextView.text = film.titre
            //   itemView.movieReleaseDateTextView.text = movie.releaseDate

            Glide
                .with(itemView.cellAfficheImageView)
                //.load(UrlBuilder.afficheUrl(film.afficheURL ?: ""))
                .load(film.afficheURL)
                .into(itemView.cellAfficheImageView)
        }
    }




}