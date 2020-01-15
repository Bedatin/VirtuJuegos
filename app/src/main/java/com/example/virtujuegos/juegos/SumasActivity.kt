package com.example.virtujuegos.juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.virtujuegos.R
import kotlinx.android.synthetic.main.activity_sumas.*

class SumasActivity : AppCompatActivity() {
    var posSoluciones: ArrayList<Int> = arrayListOf(0)
    lateinit var botones: ArrayList<Button>
    var num1 = 0
    var num2 = 0
    var solucion = 0
    var contadorVidas = 3
    var contadorRondas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumas)
        botones = arrayListOf(boton1, boton2, boton3, boton4)
        calcular()
        pulsar(boton1)
        pulsar(boton2)
        pulsar(boton3)
        pulsar(boton4)
    }
    fun replay(){
        vidas.text = "OOO"
        contadorVidas=3
        contadorRondas=0
        clickar(true)
        calcular()
    }
    fun calcular() {
        posSoluciones.clear()
        numeroRondas.text = contadorRondas.toString()
        for (i in 0 until botones.size) {
            botones[i].setBackgroundResource(R.drawable.btn_carta)
        }
        num1 = (1..9).random()
        num2 = (1..9).random()
        primerNumero.text = num1.toString()
        segundoNumero.text = num2.toString()
        solucion = num1 + num2
        posSoluciones.add(solucion)
        var x = 0
        do {
            var siOno = true
            var a = (1..20).random()
            for (i in 0 until posSoluciones.size) {
                if (a != posSoluciones[i] && siOno) {
                    siOno = true
                } else {
                    siOno = false
                }
            }
            if (siOno) {
                posSoluciones.add(a)
                x++
            }
        } while (x < 3)
        posSoluciones.shuffle()
        boton1.text = posSoluciones[0].toString()
        boton2.text = posSoluciones[1].toString()
        boton3.text = posSoluciones[2].toString()
        boton4.text = posSoluciones[3].toString()
    }
    fun pulsar(btn: Button) {
        btn.setOnClickListener {
            if (btn.text == solucion.toString()) {
                btn.setBackgroundResource(R.drawable.btn_carta_verde)
                contadorRondas++
                clickar(false)
                numeroRondas.text = contadorRondas.toString()
                Handler().postDelayed({
                    clickar(true)
                    calcular()
                },1000)
            }
            if (btn.text != solucion.toString()) {
                btn.setBackgroundResource(R.drawable.btn_carta_rojo)
                contadorVidas--
            }
            if (contadorVidas == 2) {
                vidas.text = "OOX"
            }
            if (contadorVidas == 1) {
                vidas.text = "OXX"
            }
            if (contadorVidas == 0) {
                vidas.text = "XXX"
                clickar(false)
                numeroRondas.text = contadorRondas.toString()
                val builder = AlertDialog.Builder(this)
// Set the alert dialog title
                builder.setTitle("¡LO SIENTO, HAS PERDIDO!")
// Display a message on alert dialog
                builder.setMessage("¿Quieres jugar otra partida?")
// Set a positive button and its click listener on alert dialog
                builder.setPositiveButton("Si"){dialog, which ->
                    // Do something when user press the positive button
                    //val intent = Intent(this, Emparejar::class.java)
                    //startActivity(intent)
                    replay()
                    closeContextMenu()
                }
// Display a negative button on alert dialog
                builder.setNegativeButton("No"){dialog,which ->
                    val intent = Intent(this, SumasActivity::class.java)
                    startActivity(intent)
                }
// Display a neutral button on alert dialog
// Finally, make the alert dialog using builder
                val dialog: AlertDialog = builder.create()
// Display the alert dialog on app interface
                dialog.show()
            }
        }
    }
    fun clickar(enable: Boolean) {
        boton1.isClickable = enable
        boton2.isClickable = enable
        boton3.isClickable = enable
        boton4.isClickable = enable
    }
}