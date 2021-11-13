package pe.edu.ulima.pm.pokemonanderroger.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginManager {

    companion object {
         var instance: LoginManager=LoginManager()
            private set
    }

    private val dbFirebase = Firebase.firestore


    fun saveUser(nombre: String, callbackOk: (Long) -> Unit, callbackError: (String) -> Unit) {

        val data = hashMapOf<String, Any>(
            "nombre" to nombre
        )

        val userId=System.currentTimeMillis().toString()
        dbFirebase.collection("users").document(userId).set(data)
            .addOnSuccessListener {
                callbackOk(userId.toLong())
            }.addOnFailureListener{
                callbackError(it.message!!)
            }
    }
}