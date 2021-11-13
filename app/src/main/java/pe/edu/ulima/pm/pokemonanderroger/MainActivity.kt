package pe.edu.ulima.pm.pokemonanderroger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import pe.edu.ulima.pm.pokemonanderroger.Room.PkmnAppDatabase
import pe.edu.ulima.pm.pokemonanderroger.fragments.DetallesPokemonFragment
import pe.edu.ulima.pm.pokemonanderroger.fragments.FavoritosPokemonFragment
import pe.edu.ulima.pm.pokemonanderroger.fragments.ListPokemonFragment
import pe.edu.ulima.pm.pokemonanderroger.model.*
import java.sql.Savepoint

class MainActivity : AppCompatActivity(), ListPokemonFragment.interfaceListPokemon,
    DetallesPokemonFragment.interfaceDetallePokemon/*,FavoritosPokemonFragment.interfaceFav*/ {

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

        if (pantallaFragment == 1) {
            pokemonManager = PokemonManager(this)
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.main_layout, fragments[0])
            ft.commit()

        } else {
            changeFavoritosPokemonFragment()
        }


    }

    private fun changeFavoritosPokemonFragment() {
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        //remplazar un nuevo fragment
        ft.replace(R.id.main_layout, fragment)
        ft.commit()
    }

    private fun changeDetallePokemonFragment(pokemon: PokemonFirebase) {
        val fragment = DetallesPokemonFragment(pokemon)
        val ft = supportFragmentManager.beginTransaction()
        //remplazar un nuevo fragment
        ft.replace(R.id.main_layout, fragment)
        ft.commit()
    }

    override fun OnClickRegresarButton() {
        changeListPokemonFragment()
    }

    override fun OnClickFavoritosButton() {
        //hacer la actualización a favoritos
        changeListPokemonFragment()
        Toast.makeText(this, "Se ha agregado a favoritos", Toast.LENGTH_SHORT).show()
    }

    private fun changeListPokemonFragment() {
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        //remplazar un nuevo fragment
        ft.replace(R.id.main_layout, fragment)
        ft.commit()
    }



   /* override fun onTouch(CardPokemon: PokemonFavorito) {


    }*/

    override fun onSelectCardPokemon(CardPokemon: PokemonFirebase) {
        changeDetallePokemonFragment(CardPokemon)
    }


}