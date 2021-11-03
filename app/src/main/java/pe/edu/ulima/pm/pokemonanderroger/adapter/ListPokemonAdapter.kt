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
import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
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
        val tviId: TextView

        init {

            iviPokemonImage = view.findViewById(R.id.imgPokemon)
            tviPokemonName = view.findViewById(R.id.pokemonName)
            tviPokemonHP = view.findViewById(R.id.pokemonHp)
            tviattack = view.findViewById(R.id.PokemonAttack)
            tvidefense = view.findViewById(R.id.PokemonDefense)
            tvispecialAttack = view.findViewById(R.id.specialAttack)
            tvispecialDefense = view.findViewById(R.id.specialDefense)
            tviId = view.findViewById(R.id.tviID)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(PokemonList[adapterPosition])///En que posicion se ha registrado el view holder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {//FRONTEND
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pokemon, parent, false)

        val viewHolder = ViewHolder(view, listener, ListPokemones)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//ATRIBUTOS
        println(ListPokemones)
        holder.tviId.text = ListPokemones[position].id
        holder.tviPokemonName.text = ListPokemones[position].name
      holder.tviPokemonHP.text ="Hp: ${ListPokemones[position].stats[0]!!.base_stat}"
        holder.tviattack.text ="Ataque: ${ListPokemones[position].stats[1]!!.base_stat}"
        holder.tvidefense.text ="Defensa: ${ListPokemones[position].stats[2]!!.base_stat}"
        holder.tvispecialAttack.text ="Special Attack: ${ListPokemones[position].stats[3]!!.base_stat}"
        holder.tvispecialDefense.text ="Special Defense: ${ListPokemones[position].stats[4]!!.base_stat}"

      Glide.with(fragment).load(ListPokemones[position].sprites.other.officialartwork.front_default)
           /*  .sizeMultiplier(0.2f)*/
            .fitCenter()
            .into(holder.iviPokemonImage)
    }

    override fun getItemCount(): Int {//CUANTOS SON

        return ListPokemones.size


    }

}