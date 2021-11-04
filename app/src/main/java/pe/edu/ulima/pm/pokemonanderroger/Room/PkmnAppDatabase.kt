package pe.edu.ulima.pm.pokemonanderroger.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//import pe.edu.ulima.pm.pokemonanderroger.model.Converters
import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import pe.edu.ulima.pm.pokemonanderroger.model.PokemonFavorito


@Database(entities = [Pokemon::class, PokemonFavorito::class], version = 12)
//@TypeConverters( Converters::class)
abstract class PkmnAppDatabase: RoomDatabase() {

    abstract fun PokemonDAO(): PokemonDAO
    abstract  fun PokemonFavsDAO() : PokemonFavsDAO
}