package com.example.virtujuegos.juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.virtujuegos.MenuJuegos
import com.example.virtujuegos.R
import kotlinx.android.synthetic.main.activity_tres.*

class TresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tres)

        btnUsers.setOnClickListener {
            val intent = Intent(this, TresDos::class.java)
            startActivity(intent)
        }
        btnMaquina.setOnClickListener {
            val intent = Intent(this, TresSolo::class.java)
            startActivity(intent)
        }
        btnBackTres.setOnClickListener {
            val intent = Intent(this, MenuJuegos::class.java)
            startActivity(intent)
        }
    }
}
