package pe.edu.ulima.pm.pokemonanderroger.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonFavorito

@Dao
interface PokemonFavsDAO {

    @Query("SELECT * FROM PokemonFavorito")
    fun findAllFavs(): List<PokemonFavorito>

    @Insert
    fun insertFavs(PokemonFav : PokemonFavorito)

    @Delete
    fun DeleteFav(PokemonFav: PokemonFavorito)

    @Query("SELECT count(*) FROM PokemonFavorito where id=:idd")
    fun findPok(idd:Int): Int


}