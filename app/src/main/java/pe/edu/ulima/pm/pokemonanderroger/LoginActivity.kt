package pe.edu.ulima.pm.pokemonanderroger

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.pokemonanderroger.model.LoginManager

class LoginActivity : AppCompatActivity() {
    var pantallaFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)

        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val btnFavoritos = findViewById<Button>(R.id.btnFavoritos)

        var cheq = false

        btnContinuar.setOnClickListener {
            if(findViewById<EditText>(R.id.etUser).text.toString()!=""){
                LoginManager.instance.findUser({ res ->
                    for (i in res) {
                        println(i.name)
                        if (i.name == findViewById<EditText>(R.id.etUser).text.toString()) {
                            cheq = true
                        }
                    }
                    if (cheq) {
                        Toast.makeText(this, "Ya existe un usuario con ese nombre", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "No existe agregamos chill", Toast.LENGTH_SHORT).show()
                        LoginManager.instance.saveUser(
                            findViewById<EditText>(R.id.etUser).text.toString(), {
                                val intent: Intent = Intent()
                                pantallaFragment = 1
                                val bundle: Bundle = Bundle()//Almacenamos data
                                bundle.putInt("pantallaFragment", pantallaFragment)
                                intent.setClass(this, MainActivity::class.java) //pasamos next activity
                                intent.putExtra("data", bundle)
                                startActivity(intent)
                            }, {
                                Toast.makeText(this, "Error guardado", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                }, { error ->
                    Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show()
                })

                cheq = false
            }else{
                Toast.makeText(this,"Llena serrano de mierda",Toast.LENGTH_SHORT).show()
            }



        }


        btnFavoritos.setOnClickListener {

            if(findViewById<EditText>(R.id.etUser).text.toString()!=""){
                LoginManager.instance.findUser({ res ->
                    for (i in res) {
                        println(i.name)
                        if (i.name == findViewById<EditText>(R.id.etUser).text.toString()) {
                            cheq = true
                        }
                    }
                    if (cheq) {
                        Toast.makeText(this, "Ya existe un usuario con ese nombre", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "No existe agregamos chill", Toast.LENGTH_SHORT).show()
                        LoginManager.instance.saveUser(
                            findViewById<EditText>(R.id.etUser).text.toString(), {
                                val intent: Intent = Intent()
                                pantallaFragment = 2

                                val bundle: Bundle = Bundle()//Almacenamos data
                                bundle.putInt("pantallaFragment", pantallaFragment)
                                intent.setClass(this, MainActivity::class.java) //pasamos next activity
                                intent.putExtra("data", bundle)
                                startActivity(intent)
                            }, {
                                Toast.makeText(this, "Error guardado", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                }, { error ->
                    Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show()
                })

                cheq = false
            }else{
                Toast.makeText(this,"Llena serrano de mierda",Toast.LENGTH_SHORT).show()

            }


        }



    }

}