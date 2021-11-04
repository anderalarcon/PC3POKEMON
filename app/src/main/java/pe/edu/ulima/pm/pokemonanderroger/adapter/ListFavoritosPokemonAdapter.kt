package pe.edu.ulima.pm.pokemonanderroger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonFavorito

class ListFavoritosPokemonAdapter(
    private val ListPokemonesFavs: List<PokemonFavorito>,
    private val fragment: Fragment,
    private val listener: (PokemonFavorito) -> Unit

): RecyclerView.Adapter<ListFavoritosPokemonAdapter.ViewHolder>() {
    class ViewHolder(view: View, val listener: (PokemonFavorito) -> Unit, val ListPokemonesFavs: List<PokemonFavorito>) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val tviPokemonNameFavs: TextView

        init {
            /*   iviPokemonImage = view.findViewById(R.id.imgPokemon)*/
            tviPokemonNameFavs = view.findViewById(R.id.pokemonName_favoritos)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(ListPokemonesFavs[adapterPosition])//En que posicion se ha registrado el view holder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favoritos_pokemon, parent, false)

        val viewHolder = ViewHolder(view,listener,ListPokemonesFavs)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.tviPokemonNameFavs.text = ListPokemonesFavs[position].name
    }

    override fun getItemCount(): Int {
        return ListPokemonesFavs.size
    }

}