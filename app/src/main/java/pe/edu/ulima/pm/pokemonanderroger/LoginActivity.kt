package pe.edu.ulima.pm.pokemonanderroger

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
    }
}