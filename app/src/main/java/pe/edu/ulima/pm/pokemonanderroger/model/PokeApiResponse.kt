package pe.edu.ulima.pm.pokemonanderroger.model

data class PokeApiResponse (
    var count:String,
    var next:String,
    var previous:String,
    var results: ArrayList<Pokemon>
)

