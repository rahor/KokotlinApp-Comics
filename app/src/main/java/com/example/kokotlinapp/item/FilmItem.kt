package com.example.kokotlinapp.item
/*IMPORT */
import android.view.View
import com.bumptech.glide.Glide
import com.example.kokotlinapp.R
import com.example.kokotlinapp.model.Film
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.cellule_film.view.*

class FilmItem (val film: Film) : AbstractItem<FilmItem, FilmItem.FilmViewHolder>() {

    //Pour récupérer un id de cellule
    override fun getType(): Int {
             return R.id.cellTitreTextView
    }

    // Méthode appelée lorsque l'on a besoin d'une cellule à afficher
    override fun getViewHolder(v: View): FilmViewHolder {
        return FilmViewHolder(v)
    }

    // Méthode appelée pour récupérer le layout avec un lien
    override fun getLayoutRes(): Int {
        return R.layout.cellule_film
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

            //On récupère le titre et l'image du film pour les afficher dans la cellule
            itemView.cellTitreTextView.text = film.titre

            //Chargement de l'image grâce à Glide
            Glide
                .with(itemView.cellAfficheImageView)
                .load(film.afficheURL)
                .into(itemView.cellAfficheImageView)
        }
    }




}