package pe.edu.ulima.pm.pokemonanderroger.model

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritosManager {

    companion object {
        var instance : FavoritosManager = FavoritosManager()
            private set
    }

    private val dbFirebase = Firebase.firestore

    fun saveFavorito(
        nombre: String,
        callbackOK : (Long) -> Unit,
        callbackError: (String) -> Unit
    ){

        val data = hashMapOf<String, Any>(
            "nombre" to nombre
        )
        val userId = System.currentTimeMillis()
        dbFirebase.collection("pokemons_favoritos").document(
            userId.toString()
        )
            .set(data)
            .addOnSuccessListener {
                callbackOK(userId)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }

    fun deleteFavPokemonFirebase(id : Long){

        dbFirebase.collection("pokemons_favoritos").document(id.toString())
            .delete()
            .addOnSuccessListener { Log.d("FavoritosFragment", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w("FavoritosFragment", "Error deleting document", e) }

        /*val FavReference = FirebaseDatabase.getInstance().getReference("pokemons_favoritos")
        FavReference.child().removeValue();*/

    }

}