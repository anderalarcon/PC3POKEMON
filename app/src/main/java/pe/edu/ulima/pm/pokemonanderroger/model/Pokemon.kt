package pe.edu.ulima.pm.pokemonanderroger.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Pokemon(
    var id:String,
    var name :String,
    var url:String,
    var stats:List<Habilidades>,
    var sprites:SpriteAtributes





)

