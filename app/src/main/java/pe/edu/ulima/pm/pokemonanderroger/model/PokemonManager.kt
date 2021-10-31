package pe.edu.ulima.pm.pokemonanderroger.model

import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import pe.edu.ulima.pm.pokemonanderroger.network.pokeAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class PokemonManager {
    private val mPokemones= arrayListOf<Pokemon>()
    private val imagenes = arrayListOf<String>()

/*    val API_URL =
        "https://script.google.com/macros/s/AKfycbxA2iW6e4f8IMlrIHIG_s1aRYDZDkhuKX1oKFARFFGe1du3fDM/"*/

    val API_URL ="https://pokeapi.co/api/v2/"

    init {//Inicializamos valores iniciales
        imagenes.add("https://cdn7.kiwilimon.com/recetaimagen/3329/640x426/th5-640x426-38990.jpg.webp")
        imagenes.add("https://www.recetasderechupete.com/wp-content/uploads/2020/04/Galletas-crujientes-chocolate-768x530.jpg")
        imagenes.add("https://d1kxxrc2vqy8oa.cloudfront.net/wp-content/uploads/2020/06/11193105/RFB-1305-3-galletasdemantequilla-annarecetasfacilescom.jpg")
        imagenes.add("https://t2.rg.ltmcdn.com/es/images/7/6/2/galletas_faciles_y_rapidas_32267_600.jpg")
        imagenes.add("https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-galletas-arcoirirs.png")


    }

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

    fun addPokemon(pokemon:Pokemon) {
        mPokemones.add(pokemon)
    }

    fun getProductsRetrofit(
        callbackOK: (List<Pokemon>) -> Unit,
        callbackerror: (String) -> Unit
    ) {
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(pokeAPI::class.java)

        service.getALLPokemons().enqueue(object : Callback<List<Pokemon>> {
            //estos metodos se van a llamar cuando termine la comunicacion con el servidor
          override fun onResponse(
                call: Call<List<Pokemon>>,
                response: Response<List<Pokemon>>
            ) {

                callbackOK(response.body()!!)
            }

            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                Log.e("ProductManager", t.message!!)

                callbackerror(t.message!!)
            }


        })


    }





/*
    fun getReceta(id: Int): Receta? {//Tenemos que darle ID
        val recetas=getRecetas()
        var recetaabuscar:Receta?=null

        for (i in recetas){
            if(i.id==id){
                recetaabuscar=i
            }
        }
        return recetaabuscar
    }*/
/*
    fun getIngredientes(): List<Ingrediente> {
        return mIngredientes
    }*/

    fun getImagenes():List<String>{
        return imagenes
    }

/*    fun deleteIngrediente(ingrediente:Ingrediente){
        mIngredientes.remove(ingrediente)

    }

    fun mostrarIngredientes(){
        for (i in mIngredientes){
            println(i.nombre)
        }
    }*/

    fun getRandom(): String {
        var imgs=getImagenes()

        var nro = Random.nextInt(imgs.size)

        return imgs[nro]

    }


}

