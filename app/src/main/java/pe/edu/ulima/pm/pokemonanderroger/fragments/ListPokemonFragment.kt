package pe.edu.ulima.pm.pokemonanderroger.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.Room.PkmnAppDatabase
import pe.edu.ulima.pm.pokemonanderroger.adapter.ListPokemonAdapter
import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
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
        val rvi = view.findViewById<RecyclerView>(R.id.ReciclerCardPokemon)





        PokemonManager(requireActivity().applicationContext).observe({ obs: Int ->
            if (obs == 0) {
                PokemonManager(requireActivity().applicationContext).getPokemonsPrimerNivel({ response: PokeApiResponse ->
                    rvi.adapter = ListPokemonAdapter(response.results, this) { pokemon: Pokemon ->
                        listener?.onSelectCardPokemon(pokemon)
                        println("no hay nada")
                    }
                }, { error ->
                    Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
                })
            } else {
                PokemonManager(requireActivity().applicationContext).getProductsByRoom({ response: List<Pokemon> ->
                    rvi.adapter = ListPokemonAdapter(response, this) { pokemon: Pokemon ->
                        listener?.onSelectCardPokemon(pokemon)
                        println("hay algo")
                    }
                }, { error ->
                    Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
                })
            }
        }, {

        })

    }
}