package pe.edu.ulima.pm.pokemonanderroger.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @Expose @SerializedName("name") val name: String,
/*    val hp: String,
    val attack: String,
    val defense: String,
    val specialAttack: String,
    val specialDefense: String,
    val imagen:String*/
)