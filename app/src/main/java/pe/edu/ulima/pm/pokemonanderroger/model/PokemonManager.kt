package pe.edu.ulima.pm.pokemonanderroger.model

import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import androidx.room.Room
import pe.edu.ulima.pm.pokemonanderroger.Room.PkmnAppDatabase
import pe.edu.ulima.pm.pokemonanderroger.network.pokeAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonManager(context: Context) {
    private val mPokemones = arrayListOf<Pokemon>()
    private val imagenes = arrayListOf<String>()

    val API_URL = "https://pokeapi.co/api/v2/"

    companion object {
        //reemplzao de static | Patron Singleton
        private var instance: PokemonManager? = null
    }

    /*fun getInstance(): PokemonManager {//Inicializamos
        if (instance == null) {
            var context : Context? = null
            instance = PokemonManager(context!!)
        }
        return instance!!
    }*/

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
                    println("primero: " + response.body())
                    callbackOK(response.body()!!)
                    /*        println(response.body()!!.results[0])
                            for ((index,pokemon) in response.body()!!.results.withIndex())
                            {

                                val service2 = retrofit.create(pokeAPI::class.java)

                                service2.getPokemonInfo(pokemon.url.split("/")[6].toInt())
                                    .enqueue(object : Callback<Pokemon> {
                                        override fun onResponse(
                                            call: Call<Pokemon>,
                                            response2: Response<Pokemon>
                                        ) {


                                            response.body()!!.results[index]=response2.body()!!
                                            println("SETEADO: "+response.body())

                                        }

                                        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                            }*/
                }

            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                println("error")
            }

        })

    }

/*
    fun busqueda(res: PokeApiResponse): PokeApiResponse {
        var res2:PokeApiResponse=res
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(pokeAPI::class.java)
        var handler=HandlerCompat.createAsync(Looper.myLooper()!!)
        Thread(){
            for ((i, pok) in res.results.withIndex()) {
                var list = service.getPokemonInfo2(pok.url.split("/")[6].toInt()).execute()
                res.results[i].hp=list.body()!!.hp
            }
            handler.post{res}
        }.start()

        for ((i, pok) in res.results.withIndex()) {
            service.getPokemonInfo2(pok.url.split("/")[6].toInt())
                .enqueue(object : Callback<Pokemon> {
                    override fun onResponse(
                        call: Call<Pokemon>,
                        response: Response<Pokemon>
                    ) {

                        if (response.isSuccessful) {

                             res2.results[i].hp=response.body()!!.stats[0].base_stat
                            res2.results[i].attack=response.body()!!.stats[1].base_stat
                            res2.results[i].defense=response.body()!!.stats[2].base_stat
                            res2.results[i].special_attack=response.body()!!.stats[3].base_stat
                            res2.results[i].special_defense=response.body()!!.stats[4].base_stat
                            println("PASANDO PERO NO GUARDA :S"+res2)

                        }
                    }

                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })
        }
        println("saliewndo"+res2)
        return res2

    }*/

    private val db = Room.databaseBuilder(context, PkmnAppDatabase::class.java,
        "db_pokemon").allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    private fun saveIntoRoom(pokemon: Pokemon) {
        //el it representa un item videogame que recorre

        db.PokemonDAO().insert(pokemon)


    }

    fun getProductsByRoom(callbackOK : (List<Pokemon>) -> Unit, callbackError : (String) -> Unit){
        val pokemon : List<Pokemon> = db.PokemonDAO().findAll()
        callbackOK(pokemon)
    }

    fun getPokemonsPrimerNivel(
        callbackOK: (PokeApiResponse) -> Unit,
        callbackerror: (String) -> Unit
    ) {
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(pokeAPI::class.java)

        service.getPrimerNivel().enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {

                if (response.isSuccessful()) {
                    var it: Int = 0
                    for ((i, pok) in response.body()!!.results.withIndex()) {
                        service.getPokemonInfo(pok.url!!.split("/")[6].toInt())
                            .enqueue(object : Callback<Pokemon> {
                                override fun onResponse(
                                    call: Call<Pokemon>,
                                    response2: Response<Pokemon>
                                ) {

                                    if (response2.isSuccessful()) {

                                        response.body()!!.results[i].hp =
                                            response2.body()!!.stats?.get(0)?.base_stat
                                        response.body()!!.results[i].attack =
                                            response2.body()!!.stats?.get(1)?.base_stat
                                        response.body()!!.results[i].defense =
                                            response2.body()!!.stats?.get(2)?.base_stat
                                        response.body()!!.results[i].special_attack =
                                            response2.body()!!.stats?.get(3)?.base_stat
                                        response.body()!!.results[i].special_defense =
                                            response2.body()!!.stats?.get(4)?.base_stat
                                        response.body()!!.results[i].url =
                                            response2.body()!!.sprites?.other?.officialartwork?.front_default
                                        //*   response.body()!!.results[i].stats=response2.body()!!.stats
                                        response.body()!!.results[i].sprites =
                                            response2.body()!!.sprites
                                        response.body()!!.results[i].id = response2.body()!!.id
                                        db.PokemonDAO().insert(response.body()!!.results[i])
                                        if(i==19){
                                            println(response.body())
                                            callbackOK(response.body()!!)

                                            //saveIntoRoom(response.body()!!.results[i])
                                        }

                                        it = it.plus(1)
                                    }

                                }

                                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                    Log.e("ProductManager", t.message!!)
                                    callbackerror(t.message!!)
                                }

                            })

                    }


                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                Log.e("ProductManager", t.message!!)
                callbackerror(t.message!!)
            }


        })

    }


}

