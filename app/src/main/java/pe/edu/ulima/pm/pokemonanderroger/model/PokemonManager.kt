package pe.edu.ulima.pm.pokemonanderroger.model

import kotlin.random.Random

class PokemonManager {
    private val mPokemones= arrayListOf<Pokemon>()
    private val imagenes = arrayListOf<String>()

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