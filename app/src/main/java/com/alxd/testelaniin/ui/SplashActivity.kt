package com.alxd.testelaniin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.alxd.testelaniin.R
import com.alxd.testelaniin.ui.auth.SignInActivity
import com.alxd.testelaniin.utils.LocalStorage
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()
        val user =mAuth.currentUser

        //iniciando paperDB
        LocalStorage.initPaper(this)

        //Si el usuario no se ha auntenticado se mostrara la vista para autenticar, caso contrario entrara a la vista principal
        Handler(Looper.getMainLooper()).postDelayed({
            if(user != null){
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }else{
                val signInIntent = Intent(this, SignInActivity::class.java)
                startActivity(signInIntent)
                finish()
            }
        }, 3000)

    }
}