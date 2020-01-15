package com.example.virtujuegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_ranking)

        btnRegistro.setOnClickListener {
            //supportFragmentManager.beginTransaction().replace(R.id.containerRanking, RegistroFragment(), "MiFragment").commit()
            val intent = Intent(this, ResgistroActivity::class.java)
            startActivity(intent)
        }
    }
}
