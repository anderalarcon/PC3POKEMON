package pe.edu.ulima.pm.pokemonanderroger.model

import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import androidx.room.Room
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.pokemonanderroger.Room.PkmnAppDatabase
import pe.edu.ulima.pm.pokemonanderroger.network.pokeAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonManager(context: Context) {

    //firebase

    val dbFirebase = Firebase.firestore


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


    val db = Room.databaseBuilder(
        context, PkmnAppDatabase::class.java,
        "db_pokemon"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    fun saveIntoRoomFavs(pokemonFavs: PokemonFavorito) {
        //el it representa un item videogame que recorre

        db.PokemonFavsDAO().insertFavs(pokemonFavs)
    }

    fun getPokemonFavsByRoom(
        callbackOK: (List<PokemonFavorito>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        val pokemon: List<PokemonFavorito> = db.PokemonFavsDAO().findAllFavs()
        callbackOK(pokemon)
    }

    private fun saveIntoRoom(pokemon: Pokemon) {
        //el it representa un item videogame que recorre

        db.PokemonDAO().insert(pokemon)


    }

    fun getProductsByRoom(callbackOK: (List<Pokemon>) -> Unit, callbackError: (String) -> Unit) {
        val pokemon: List<Pokemon> = db.PokemonDAO().findAll()
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
                                        savePokemonToFirebase(
                                            response.body()!!.results[i].id!!,
                                            response.body()!!.results[i].name!!,
                                            response.body()!!.results[i].url!!,
                                            response.body()!!.results[i].hp!!,
                                            response.body()!!.results[i].attack!!,
                                            response.body()!!.results[i].defense!!,
                                            response.body()!!.results[i].special_attack!!,
                                            response.body()!!.results[i].special_defense!!,
                                            {},
                                            {})
                                        /*    db.PokemonDAO().insert(response.body()!!.results[i])*/
                                        if (i == response.body()!!.results.size - 1) {
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

    fun observev2(  callbackOK: (Int) -> Unit,
                    callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("pokemons").get().addOnSuccessListener { res ->
            callbackOK(res.size())

        }.addOnFailureListener {
            callbackError(it.message!!)
        }
    }

    fun observe(callbackOK: (Int) -> Unit, callbackError: (String) -> Unit) {
        val pokemon: Int = db.PokemonDAO().selectAllUsers()
        callbackOK(pokemon)
    }

    fun deleteFav(pok: PokemonFavorito) {
        db.PokemonFavsDAO().DeleteFav(pok)

    }


    fun getFav(pok: PokemonFirebase): Int {
        return db.PokemonFavsDAO().findPok(pok.id!!.toInt())
    }

    fun savePokemonToFirebase(
        id:Int,
        name: String,
        url: String,
        hp: String,
        attack: String,
        defense: String,
        special_attack: String,
        special_defense: String,

        callbackOK: (String) -> Unit,
        callbackError: (String) -> Unit
    ) {
        val data = hashMapOf<String, Any>(
            "ID" to id,
            "name" to name,
            "url" to url,
            "hp" to hp,
            "attack" to attack,
            "defense" to defense,
            "special_attack" to special_attack,
            "special_defense" to special_defense,

        )

        dbFirebase.collection("pokemons").document(
            id.toString()
        ).set(data).addOnSuccessListener {
            callbackOK(id.toString())
        }.addOnFailureListener { callbackError(it.message!!) }
    }

    fun getPokemonsFirebase(
        callbackOK: (List<PokemonFirebase>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("pokemons").orderBy("ID").get().addOnSuccessListener { res ->
            val pokemons = arrayListOf<PokemonFirebase>()
            for (document in res) {
                val pokemon = PokemonFirebase(
                    document.id.toLong(),
                    document.data["name"] as String?,
                    document.data["url"] as String?,
                    document.data["hp"] as String?,
                    document.data["attack"] as String?,
                    document.data["defense"] as String?,
                    document.data["special_attack"] as String?,
                    document.data["special_defense"] as String?
                )
                pokemons.add(pokemon)

            }
            callbackOK(pokemons)
        }.addOnFailureListener {
            callbackError(it.message!!)
        }
    }

    fun getPokemonsFavsFirebase(
        callbackOK: (List<PokemonFavFirebase>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("pokemons_favoritos").get().addOnSuccessListener { res ->
            val pokemons = arrayListOf<PokemonFavFirebase>()
            for (document in res) {
                val pokemon = PokemonFavFirebase(
                    document.id.toLong(),
                    document.data["nombre"] as String?,
                )
                pokemons.add(pokemon)

            }
            callbackOK(pokemons)
        }.addOnFailureListener {
            callbackError(it.message!!)
        }
    }


}

