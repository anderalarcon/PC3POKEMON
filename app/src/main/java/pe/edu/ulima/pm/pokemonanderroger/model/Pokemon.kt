package pe.edu.ulima.pm.pokemonanderroger.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Pokemon(
    var id:String,
    var name :String,
    var url:String,
    var hp:String,
    var attack:String,
    var defense:String,
    var special_attack :String,
    var special_defense:String,







    var stats:List<Habilidades>,
    var sprites:SpriteAtributes





)

