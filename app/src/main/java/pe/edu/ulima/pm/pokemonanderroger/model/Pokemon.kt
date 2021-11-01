package pe.edu.ulima.pm.pokemonanderroger.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    var id :Int,
    var name:String,
    var stats:ArrayList<Stat>


)

data class Stat(
    var base_stat:String,
    var stat:String
)