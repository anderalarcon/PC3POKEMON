package pe.edu.ulima.pm.pokemonanderroger.model

data class PokeApiResponse (
    var name:String,
    var results: ArrayList<Pokemon>,
    var stats:ArrayList<Habilidades>
)

