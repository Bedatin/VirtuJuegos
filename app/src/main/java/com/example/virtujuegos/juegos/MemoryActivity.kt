package com.example.virtujuegos.juegos

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.example.virtujuegos.Dialogo
import com.example.virtujuegos.MenuJuegos
import com.example.virtujuegos.R
import kotlinx.android.synthetic.main.activity_memory.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MemoryActivity : AppCompatActivity() {

    lateinit var mp: MediaPlayer

    var codigo = arrayListOf<Int>()
    var jugada = arrayListOf<Int>()
    var turno = 0
    var cantando = true
    var cuentaCanto = 0

    var sonido = true

    var check: Boolean = true

    val info:Int = R.string.MemoDescription


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_memory)

        btnBackMemo.setOnClickListener {
            val intent = Intent(this, MenuJuegos::class.java)
            startActivity(intent)
        }

        btnMute.setOnClickListener {
            sonido = if (sonido) {
                btnMute.setBackgroundResource(R.drawable.ic_mute)
                false
            } else {
                btnMute.setBackgroundResource(R.drawable.ic_volume)
                true
            }
        }

        btnReplay.setOnClickListener {
            //Si quito las dos primeras lineas empiezo de cero con el mismo codigo
            codigo.clear()
            generaCode()
            cantando = true
            jugada.clear()
            check = true
            turno = 0
            cantaPri(codigo[0])
            txtCheck.text = "Jugada nº ${turno+1}"
            turno++
            clickar(true)
            Log.i("myapp", codigo.toString())
        }

        btnInfoMemo.setOnClickListener {
            btnInfoMemo.setBackgroundResource(R.drawable.ic_info_outline)
            PopMemo()
        }

        generaCode()
        // ver numeros en ord


        if (turno == 0) {
            clickar(false)
            turno++
            cantaPri(codigo[0])
        }

        btnHipo.setOnClickListener {
            mp = MediaPlayer.create(this, R.raw.bongo)
            if (sonido) {
                mp.start()
            }
            clickar(false)
            btnHipo.setBackgroundResource(R.drawable.btn_carta_rojo)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnHipo.setBackgroundResource(R.drawable.btn_borde_rojo)
                clickar(true)
            }
            jugada.add(1)
            if (txtCantando.text != "Ganaste") {
                checkCode()
            }
        }
        btnCoco.setOnClickListener {
            mp = MediaPlayer.create(this, R.raw.piano)
            if (sonido) {
                mp.start()
            }
            clickar(false)
            btnCoco.setBackgroundResource(R.drawable.btn_carta_verde)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnCoco.setBackgroundResource(R.drawable.btn_borde_verde)
                clickar(true)
            }
            jugada.add(2)
            if (txtCantando.text != "Ganaste") {
                checkCode()
            }
        }
        btnPato.setOnClickListener {
            mp = MediaPlayer.create(this, R.raw.saxofon)
            if (sonido) {
                mp.start()
            }
            clickar(false)
            btnPato.setBackgroundResource(R.drawable.btn_carta)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnPato.setBackgroundResource(R.drawable.btn_borde_naranja)
                clickar(true)
            }
            jugada.add(3)
            if (txtCantando.text != "Ganaste") {
                checkCode()
            }
        }
        btnRana.setOnClickListener {
            mp = MediaPlayer.create(this, R.raw.tambor)
            if (sonido) {
                mp.start()
            }
            clickar(false)
            btnRana.setBackgroundResource(R.drawable.btn_carta_blanco)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnRana.setBackgroundResource(R.drawable.btn_borde_blanco)
                clickar(true)
            }
            jugada.add(4)
            if (txtCantando.text != "Ganaste") {
                checkCode()
            }
        }
    }

    fun PopMemo() {
        val newFragment = Dialogo(info)
        newFragment.show(supportFragmentManager, "Info")
    }

    fun clickar(enable: Boolean) {
        btnPato.isClickable = enable
        btnRana.isClickable = enable
        btnHipo.isClickable = enable
        btnCoco.isClickable = enable
    }

    fun generaCode() {
        for (i in 0..14) {
            codigo.add((1..4).random())
        }
    }

    fun cantaPri(btn: Int) {
        txtCheck.text = "Jugada nº $turno"
        txtCantando.text = "Cantando"
        clickar(false)
        if (btn == 1) {
            val mp = MediaPlayer.create(this, R.raw.bongo)
            if (sonido) {
                mp.start()
            }
            btnHipo.setBackgroundResource(R.drawable.btn_carta_rojo)
            //CoroutineScope(Dispatchers.Main).launch {
            //delay(500)
            Handler().postDelayed({
                btnHipo.setBackgroundResource(R.drawable.btn_borde_rojo)
                cuentaCanto++
                if (cuentaCanto == turno) {
                    txtCantando.text = "Te toca"
                    cuentaCanto = 0
                    clickar(true)
                }
            },500)
            //}
        }
        else if (btn == 2) {
            val mp = MediaPlayer.create(this, R.raw.piano)
            if (sonido) {
                mp.start()
            }
            btnCoco.setBackgroundResource(R.drawable.btn_carta_verde)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnCoco.setBackgroundResource(R.drawable.btn_borde_verde)
                cuentaCanto++
                if (cuentaCanto == turno) {
                    txtCantando.text = "Te toca"
                    cuentaCanto = 0
                    clickar(true)
                }
            }
        }
        else if (btn == 3) {
            val mp = MediaPlayer.create(this, R.raw.saxofon)
            if (sonido) {
                mp.start()
            }
            btnPato.setBackgroundResource(R.drawable.btn_carta)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnPato.setBackgroundResource(R.drawable.btn_borde_naranja)
                cuentaCanto++
                if (cuentaCanto == turno) {
                    txtCantando.text = "Te toca"
                    cuentaCanto = 0
                    clickar(true)
                }
            }
        }
        else if (btn == 4) {
            val mp = MediaPlayer.create(this, R.raw.tambor)
            if (sonido) {
                mp.start()
            }
            btnRana.setBackgroundResource(R.drawable.btn_carta_blanco)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                btnRana.setBackgroundResource(R.drawable.btn_borde_blanco)
                cuentaCanto++
                if (cuentaCanto == turno) {
                    txtCantando.text = "Te toca"
                    cuentaCanto = 0
                    clickar(true)
                }
            }
        }
    }

    fun cantaCode() {
        if (check) {
            CoroutineScope(Dispatchers.Main).launch {
                for (i in 0 until turno) {
                    delay(1000)
                    cantaPri(codigo[i])
                }
            }
        }
    }

    fun checkCode() {
        var count = 0
        for (i in 0 until jugada.size) {
            check = jugada[i] == codigo[i] && check
            if (check) {
                count++
            }
        }
        if (!check) {
            txtCantando.text = "Perdiste"
            txtCheck.text = "Perdiste"
            cantando = false
        }
        if (count == turno) {
            cantando = false
            if (count == codigo.size) {
                txtCantando.text = "Ganaste"
                txtCheck.text = "Ganaste"
                turno = 0
            }
        }
        if (!cantando && turno == jugada.size) {
            jugada.clear()
            cantando = true
            cantaCode()
            turno++
        }
    }
}
