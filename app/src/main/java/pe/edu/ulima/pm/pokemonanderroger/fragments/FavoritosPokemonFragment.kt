package pe.edu.ulima.pm.pokemonanderroger.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.adapter.ListFavoritosPokemonAdapter
import pe.edu.ulima.pm.pokemonanderroger.adapter.ListPokemonAdapter
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonManager

class FavoritosPokemonFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos_pokemon,container,false)
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvi = view.findViewById<RecyclerView>(R.id.ReciclerFavsPokemon)

        *//*rvi.adapter = ListFavoritosPokemonAdapter(

        )*//*

    }*/

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val rvi = view.findViewById<RecyclerView>(R.id.ReciclerFavsPokemon)

        rvi.adapter =ListFavoritosPokemonAdapter(
            PokemonManager(requireActivity().applicationContext).getPokemones(),
            this,
            {
                    p:Pokemon ->


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