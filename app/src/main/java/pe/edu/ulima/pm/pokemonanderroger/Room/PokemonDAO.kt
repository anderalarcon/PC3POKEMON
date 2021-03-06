package pe.edu.ulima.pm.pokemonanderroger.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import androidx.lifecycle.LiveData





@Dao
interface PokemonDAO {

    @Query("SELECT * FROM Pokemon order by id ASC")
    fun findAll(): List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE id=:id")
    fun findById( id: String): Pokemon



    @Insert
    fun insert(pokemon: Pokemon)

    @Query("SELECT count(*) from Pokemon")
    fun selectAllUsers(): Int

}