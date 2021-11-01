package pe.edu.ulima.pm.pokemonanderroger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.pokemonanderroger.fragments.FavoritosPokemonFragment
import pe.edu.ulima.pm.pokemonanderroger.fragments.ListPokemonFragment
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonManager

class MainActivity : AppCompatActivity(),ListPokemonFragment.interfaceListPokemon {

    val fragments = mutableListOf<Fragment>()
    var pokemonManager: PokemonManager? = null
    var pantallaFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        pantallaFragment = intent.getBundleExtra("data")?.getInt("pantallaFragment")!!
        println("pantalla: " + pantallaFragment)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.main)

        if (fragments.size == 0) {
            fragments.add(ListPokemonFragment())
            fragments.add(FavoritosPokemonFragment())
        }

      if (pantallaFragment == 1){
            pokemonManager = PokemonManager().getInstance()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.main_layout, fragments[0])
            ft.commit()
        }else{
            changeFavoritosPokemonFragment()
        }

      var pokemon1: Pokemon = Pokemon(
            "Ditto",
            "hp: 45",
            "attack: 45",
            "defense: 100",
            "especial attack: 100",
            "especial defense: 100",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png"
        )
        pokemonManager?.addPokemon(pokemon1)

    }

    private fun changeFavoritosPokemonFragment(){
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        //remplazar un nuevo fragment
        ft.replace(R.id.main_layout,fragment)
        ft.commit()
    }

    override fun onSelectCardPokemon(CardPokemon: Pokemon) {
        Toast.makeText(this,"tocaste",Toast.LENGTH_SHORT).show()

  /*      println(CardPokemon.nombre)
        println(CardPokemon.attack)
        println(CardPokemon.defense)
        println(CardPokemon.specialAttack)
        println(CardPokemon.specialDefense)
*/

    }


}