package pe.edu.ulima.pm.pokemonanderroger.network

import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface pokeAPI {


    @GET("pokemon/")
    fun getALLPokemons(): Call<PokeApiResponse>

    @GET("{id}")
    fun getPokemonInfo(@Path(value = "id")Id:Int): Call<Pokemon>
}