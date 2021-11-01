package pe.edu.ulima.pm.pokemonanderroger

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:AppCompatActivity() {
    var pantallaFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)

        val btnContinuar=findViewById<Button>(R.id.btnContinuar)
        val btnFavoritos=findViewById<Button>(R.id.btnFavoritos)



        btnContinuar.setOnClickListener{
            val intent: Intent = Intent()
            pantallaFragment = 1
            val bundle:Bundle= Bundle()//Almacenamos data
            bundle.putInt("pantallaFragment",pantallaFragment)
            intent.setClass(this, MainActivity::class.java) //pasamos next activity
            intent.putExtra("data",bundle)
            startActivity(intent)
        }
        btnFavoritos.setOnClickListener{
            pantallaFragment = 2
            val intent: Intent = Intent()
            val bundle:Bundle= Bundle()//Almacenamos data
            bundle.putInt("pantallaFragment",pantallaFragment)
            intent.setClass(this, MainActivity::class.java) //pasamos next activity
            intent.putExtra("data",bundle)
            startActivity(intent)

        }
    }
}