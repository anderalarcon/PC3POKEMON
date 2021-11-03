package pe.edu.ulima.pm.pokemonanderroger.network

import pe.edu.ulima.pm.pokemonanderroger.model.PokeApiResponse
import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface pokeAPI {


    @GET("pokemon/")
    fun getALLPokemons(): Call<PokeApiResponse>

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path(value = "id")Id:Int): Call<Pokemon>


    @GET("pokemon?limit=20&offset=0")
    fun getPrimerNivel(): Call<PokeApiResponse>



    @GET("pokemon/{id}")
    fun getPokemonInfo2(@Path(value = "id")Id:Int): Call<Pokemon>
}