package pe.edu.ulima.pm.pokemonanderroger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon

class ListPokemonAdapter(
    private val ListPokemones: List<Pokemon>,
    private val fragment: Fragment,
    private val listener: (Pokemon) -> Unit

) : RecyclerView.Adapter<ListPokemonAdapter.ViewHolder>() {
    // clase que va representar 1 item visual , este necesita los elementos del item , implementamos los metodos que solicita
    class ViewHolder(view: View, val listener: (Pokemon) -> Unit, val PokemonList: List<Pokemon>) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val iviPokemonImage: ImageView
        val tviPokemonName: TextView
        val tviPokemonHP: TextView
        val tviattack: TextView
        val tvidefense: TextView
        val tvispecialAttack: TextView
        val tvispecialDefense: TextView

        init {
            iviPokemonImage = view.findViewById(R.id.imgPokemon)
            tviPokemonName = view.findViewById(R.id.pokemonName)
            tviPokemonHP = view.findViewById(R.id.pokemonHp)
            tviattack = view.findViewById(R.id.PokemonAttack)
            tvidefense = view.findViewById(R.id.PokemonDefense)
            tvispecialAttack = view.findViewById(R.id.specialAttack)
            tvispecialDefense = view.findViewById(R.id.specialDefense)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(PokemonList[adapterPosition])//En que posicion se ha registrado el view holder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {//FRONTEND
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pokemon, parent, false)

        val viewHolder = ViewHolder(view, listener, ListPokemones)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//ATRIBUTOS
        holder.tviPokemonName.text = ListPokemones[position].nombre
        holder.tviPokemonHP.text = ListPokemones[position].hp
        holder.tviattack.text = ListPokemones[position].attack
        holder.tvidefense.text = ListPokemones[position].defense
        holder.tvispecialAttack.text = ListPokemones[position].specialAttack
        holder.tvispecialDefense.text = ListPokemones[position].specialDefense

        Glide.with(fragment).load(ListPokemones[position].imagen)
             .sizeMultiplier(0.2f)
            .fitCenter()
            .into(holder.iviPokemonImage)
    }

    override fun getItemCount(): Int {//CUANTOS SON
        return ListPokemones.size
    }

}