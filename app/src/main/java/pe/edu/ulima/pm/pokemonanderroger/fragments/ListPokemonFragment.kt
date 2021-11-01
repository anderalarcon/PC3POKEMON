package pe.edu.ulima.pm.pokemonanderroger.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.adapter.ListPokemonAdapter
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonManager

class ListPokemonFragment : Fragment() {

    interface interfaceListPokemon {
        //al hacer touch en el activity main
        fun onSelectCardPokemon(CardPokemon: Pokemon)
    }

    private var listener: interfaceListPokemon? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceListPokemon
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_pokemon, container, false)

    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)


       val rviProducts =
            view.findViewById<RecyclerView>(R.id.ReciclerCardPokemon)
        var ascasc = rviProducts
        ascasc.adapter =ListPokemonAdapter(
            PokemonManager().getInstance().getPokemones(),
            this,
            {
                    p:Pokemon ->

                listener?.onSelectCardPokemon(p)
            })

/*
      PokemonManager().getInstance().getProductsRetrofit({ pokemonList: List<Pokemon> ->
            val rvi = view.findViewById<RecyclerView>(R.id.ReciclerCardPokemon)

            rvi.adapter = ListPokemonAdapter(
                pokemonList,
                this
            ) { pokemon: Pokemon ->
                listener?.onSelectCardPokemon(pokemon)
            }
        }, { error ->
            Toast.makeText(activity, "$error", Toast.LENGTH_SHORT).show()
        })*/


    }
}