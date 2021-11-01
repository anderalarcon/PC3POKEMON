package pe.edu.ulima.pm.pokemonanderroger.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    var name :String,
    var url:String,
    var stats:ArrayList<Habilidades>



)

