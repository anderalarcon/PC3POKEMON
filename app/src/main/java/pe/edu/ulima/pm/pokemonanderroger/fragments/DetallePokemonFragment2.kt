package pe.edu.ulima.pm.pokemonanderroger.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import pe.edu.ulima.pm.pokemonanderroger.R
import pe.edu.ulima.pm.pokemonanderroger.model.FavoritosManager
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonFirebase

class DetallesPokemonFragment2(
    val pokemon: Pokemon
    /*val nombre:String,
    var HP:String,
    var attack:String,
    var defense:String,
    var special_attack:String,
    var special_defense:String*/): Fragment() {

    interface interfaceDetallePokemon {
        //al hacer touch en el activity main
        fun OnClickRegresarButton2()
        fun OnClickFavoritosButton2()
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

        var cheq = false
        val butRegresar = view.findViewById<Button>(R.id.btnRegresarListaPokemon)
        val butFavoritos = view.findViewById<Button>(R.id.btnFavoritosListaPokemon)

        FavoritosManager.instance.checkFavsPokemon({ res ->
            for (i in res) {

                if (i.name.toString() == pokemon.name.toString()) {
                    println("NOMBRE POKEMON: " + pokemon.name)
                    println("nombre lista: " + i.name)

                    cheq = true
                    println("CHEEECCK: " + cheq)
                }

                if(cheq/*PokemonManager(requireActivity().applicationContext).getFav(pokemon)==0*/){
                    println("ENTRO INVISBLE")
                    butFavoritos.visibility= View.INVISIBLE

                }else{
                    println("ENTRO VISIBLE")
                    butFavoritos.visibility= View.VISIBLE
                }
            }
        }, {
                error -> print(error)
        }


        )



        butRegresar.setOnClickListener { _: View ->
            listener?.OnClickRegresarButton2()
        }
        println("CHEQQQQQQ: " + cheq)


        butFavoritos.setOnClickListener { _: View ->

            FavoritosManager.instance.saveFavorito(
                pokemon.name.toString(),
                {

                    println(it)
                    //sendNotification(createNotification("1"))

                },
                {
                    Log.e("AgregarFavoritosPokemon", it)
                    //Toast.makeText(this, "Error guardando usuario", Toast.LENGTH_SHORT).show()
                }
            )

            /*var pokemonfav = PokemonFavorito(pokemon.id, pokemon.name)
            PokemonManager(requireActivity().applicationContext).saveIntoRoomFavs(pokemonfav)*/
            listener?.OnClickFavoritosButton2()

        }

    }
}