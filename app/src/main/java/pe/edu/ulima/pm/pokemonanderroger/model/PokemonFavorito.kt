package pe.edu.ulima.pm.pokemonanderroger.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonFavorito(
    @PrimaryKey
    var id:Int?,
    @ColumnInfo( name = "name")
    var name :String?
    /*@ColumnInfo( name = "url")
    var url:String?,
    @ColumnInfo( name = "hp")
    var hp:String?,
    @ColumnInfo( name = "attack")
    var attack:String?,
    @ColumnInfo( name = "defense")
    var defense:String?,
    @ColumnInfo( name = "special_attack")
    var special_attack :String?,
    @ColumnInfo( name = "special_defense")
    var special_defense:String?,
*/
    )