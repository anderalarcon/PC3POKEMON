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
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonFavorito
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonManager

class FavoritosPokemonFragment : Fragment() {

    interface interfaceFav {
        //al hacer touch en el activity main
        fun onTouch(CardPokemon: PokemonFavorito)
    }

    private var listener: interfaceFav? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceFav
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos_pokemon, container, false)
    }


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val rvi = view.findViewById<RecyclerView>(R.id.ReciclerFavsPokemon)

        PokemonManager(requireActivity().applicationContext).getPokemonFavsByRoom({ response: List<PokemonFavorito> ->
            rvi.adapter = ListFavoritosPokemonAdapter(response, this) {

                    pokemon: PokemonFavorito ->
                rvi.adapter!!.notifyItemRemoved(pokemon.id!!.toInt())
                listener?.onTouch(pokemon)

            }
        }, { error ->
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        })


    }
}