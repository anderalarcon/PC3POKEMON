package pe.edu.ulima.pm.pokemonanderroger

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)

        val btnContinuar=findViewById<Button>(R.id.btnContinuar)
        val btnFavoritos=findViewById<Button>(R.id.btnFavoritos)
        val intent: Intent = Intent()

        btnContinuar.setOnClickListener{

            intent.setClass(this, MainActivity::class.java) //pasamos next activity
            startActivity(intent)
        }
        btnFavoritos.setOnClickListener{
            Toast.makeText(this,"QUE MANDE A FRAGMENT FAVORITOS",Toast.LENGTH_SHORT).show()

        }
    }
}