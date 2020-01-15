package com.example.virtujuegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.virtujuegos.juegos.EmparejarActivity
import com.example.virtujuegos.juegos.MemoryActivity
import com.example.virtujuegos.juegos.SumasActivity
import com.example.virtujuegos.juegos.TresActivity
import kotlinx.android.synthetic.main.activity_menu_juegos.*

class MenuJuegos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_menu_juegos)

        btnBackMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSumas.setOnClickListener {
            val intent = Intent(this, SumasActivity::class.java)
            startActivity(intent)
        }
        btnMemory.setOnClickListener {
            val intent = Intent(this, MemoryActivity::class.java)
            startActivity(intent)
        }
        btnEmpareja.setOnClickListener {
            val intent = Intent(this, EmparejarActivity::class.java)
            startActivity(intent)
        }
        btn3enRaya.setOnClickListener {
            val intent = Intent(this, TresActivity::class.java)
            startActivity(intent)
        }
    }

}

