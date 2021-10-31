package pe.edu.ulima.pm.pokemonanderroger.network

import pe.edu.ulima.pm.pokemonanderroger.model.Pokemon

import retrofit2.Call
import retrofit2.http.GET

interface pokeAPI {


    @GET("pokemon/ditto")
    fun getALLPokemons(): Call<List<Pokemon>>
}