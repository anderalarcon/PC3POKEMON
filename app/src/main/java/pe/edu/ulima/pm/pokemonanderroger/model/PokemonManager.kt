package pe.edu.ulima.pm.pokemonanderroger.model

import pe.edu.ulima.pm.pokemonanderroger.network.pokeAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonManager {
    private val mPokemones = arrayListOf<Pokemon>()
    private val imagenes = arrayListOf<String>()

    val API_URL = "https://pokeapi.co/api/v2/"

    companion object {
        //reemplzao de static | Patron Singleton
        private var instance: PokemonManager? = null
    }

    fun getInstance(): PokemonManager {//Inicializamos
        if (instance == null) {
            instance = PokemonManager()
        }
        return instance!!
    }

    fun getPokemones(): List<Pokemon> {
        return mPokemones
    }

    fun addPokemon(pok: Pokemon) {
        mPokemones.add(pok)
    }


    fun getPokemonsRetrofit(
        callbackOK: (PokeApiResponse) -> Unit,
        callbackerror: (String) -> Unit
    ) {
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(pokeAPI::class.java)

        service.getALLPokemons().enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                if (response.isSuccessful()) {
                    callbackOK(response.body()!!)
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                println("error")
            }

        })

    }

    fun getPokemonsRetrofit2(id:Int) {
        val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(pokeAPI::class.java)

        service.getPokemonInfo(id).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful()) {
                    println(response.body())
                }

            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                println("err")
            }


        })

    }


}

