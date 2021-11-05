package pe.edu.ulima.pm.pokemonanderroger.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.bumptech.glide.Glide
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.Room.PkmnAppDatabase
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonFavorito
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonManager


class DetallesPokemonFragment(
    val pokemon: Pokemon
    /*val nombre:String,
    var HP:String,
    var attack:String,
    var defense:String,
    var special_attack:String,
    var special_defense:String*/): Fragment() {

    interface interfaceDetallePokemon {
        //al hacer touch en el activity main
        fun OnClickRegresarButton()
        fun OnClickFavoritosButton()
    }

    private var listener: interfaceDetallePokemon? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceDetallePokemon
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_pokemon,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.NombrePokemon_Detalle).setText(pokemon.name)
        view.findViewById<TextView>(R.id.PokemonHp_Detalle).setText(pokemon.hp)
        view.findViewById<TextView>(R.id.PokemonAttack_Detalle).setText(pokemon.attack)
        view.findViewById<TextView>(R.id.PokemonDefense_Detalle).setText(pokemon.defense)
        view.findViewById<TextView>(R.id.PokemonspecialAttack_Detalle).setText(pokemon.special_attack)
        view.findViewById<TextView>(R.id.PokemonspecialDefense_Detalle).setText(pokemon.special_defense)

        Glide.with(view).load(pokemon.url)
            /*  .sizeMultiplier(0.2f)*/
            .fitCenter()
            .into(view.findViewById(R.id.imageView_Detalle))


        val butRegresar = view.findViewById<Button>(R.id.btnRegresarListaPokemon)
        val butFavoritos = view.findViewById<Button>(R.id.btnFavoritosListaPokemon)

        butRegresar.setOnClickListener { _: View ->
            listener?.OnClickRegresarButton()
        }






        if(PokemonManager(requireActivity().applicationContext).getFav(pokemon)==0){

            butFavoritos.visibility=View.VISIBLE

        }else{

            butFavoritos.visibility=View.INVISIBLE
        }

        butFavoritos.setOnClickListener { _: View ->

            var pokemonfav = PokemonFavorito(pokemon.id, pokemon.name)
            PokemonManager(requireActivity().applicationContext).saveIntoRoomFavs(pokemonfav)
            listener?.OnClickFavoritosButton()

        }

    }
}