package pe.edu.ulima.pm.pokemonanderroger.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//import pe.edu.ulima.pm.pokemonanderroger.model.Converters
import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon

@Database(entities = [Pokemon::class], version = 8)
//@TypeConverters( Converters::class)
abstract class PkmnAppDatabase: RoomDatabase() {

    abstract fun PokemonDAO(): PokemonDAO
}