package com.example.virtujuegos.juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import com.example.virtujuegos.Dialogo
import com.example.virtujuegos.R
import kotlinx.android.synthetic.main.activity_tres_solo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TresSolo : AppCompatActivity() {

    var turno = (0..1).random()
    var dificultad = ""
    var difi = 0
    val letraOrd = "X"
    val letraUser = "O"
    var fin = false
    var clickable = true
    var partidas = 0

    var drawOrd = R.drawable.ic_launcher_foreground
    var drawUser = R.drawable.ic_person_white_24dp

    //turnos al revés para que te diga el turno del siguiente
    var turnoOrd = R.string.Turno_Lea
    var turnoUser = R.string.Turno_Pop

    var ganaOrd = R.string.Gana_Pop
    var ganaUser = R.string.Gana_Lea

    val info:Int = R.string.TeRDescription


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tres_solo)

        btnBackMaq.setOnClickListener {
            val intent = Intent(this, TresActivity::class.java)
            startActivity(intent)
        }

        btnInfoTres.setOnClickListener {
            btnInfoTres.setBackgroundResource(R.drawable.ic_info_outline)
            PopMemo()
        }


        btnLea.setOnClickListener {
            drawOrd = R.drawable.ic_launcher_foreground
            drawUser = R.drawable.ic_person_white_24dp

            //turnos al revés para que te diga el turno del siguiente
            turnoOrd = R.string.Turno_Lea
            turnoUser = R.string.Turno_Pop

            ganaOrd = R.string.Gana_Pop
            ganaUser = R.string.Gana_Lea

            deleteTable()
        }

        btnPop.setOnClickListener {
            drawOrd = R.drawable.ic_person_white_24dp
            drawUser = R.drawable.ic_launcher_foreground

            //turnos al revés para que te diga el turno del siguiente
            turnoOrd = R.string.Turno_Pop
            turnoUser = R.string.Turno_Lea

            ganaOrd = R.string.Gana_Lea
            ganaUser = R.string.Gana_Pop

            deleteTable()
        }

        btnFacil.setOnClickListener {
            dificultad = btnFacil.text.toString()
            difi = 1
            deleteTable()
        }
        btnMedia.setOnClickListener {
            dificultad = btnMedia.text.toString()
            difi = 2
            deleteTable()
        }
        btnDificil.setOnClickListener {
            dificultad = btnDificil.text.toString()
            difi = 3
            deleteTable()
            /*btnFacil.setBackgroundResource(R.drawable.btn_round_20)
            btnMedia.setBackgroundResource(R.drawable.btn_round_20)
            btnDificil.setBackgroundResource(R.drawable.btn_borde_carta)*/
        }
        //Hacer tablero cuadrado
        cuadro.layoutParams.height = windowManager.defaultDisplay.width

        if (partidas == 0) {
            if (turno == 1) {
                txtArriba.text = getText(turnoUser)
                CoroutineScope(Dispatchers.Main).launch {
                    //Thread.sleep(3500)
                    delay(350)
                    jugadadaOrd()
                    ganarXO()
                    turno = 0
                    clickable = true
                    clickar(clickable)
                    txtArriba.text = getText(turnoOrd)
                }
            } else {
                txtArriba.text = getText(turnoOrd)
            }
        }

        PartidaOrd(btn11)
        PartidaOrd(btn12)
        PartidaOrd(btn13)
        PartidaOrd(btn21)
        PartidaOrd(btn22)
        PartidaOrd(btn23)
        PartidaOrd(btn31)
        PartidaOrd(btn32)
        PartidaOrd(btn33)

        btnReplay.setOnClickListener {
            deleteTable()
        }
    }

    fun PopMemo() {
        val newFragment = Dialogo(info)
        newFragment.show(supportFragmentManager, "Info")
    }

    fun deleteTable() {
        btn11.text = ""
        btn11.setBackgroundResource(R.color.colorTransparente)
        btn12.text = ""
        btn12.setBackgroundResource(R.color.colorTransparente)
        btn13.text = ""
        btn13.setBackgroundResource(R.color.colorTransparente)
        btn21.text = ""
        btn21.setBackgroundResource(R.color.colorTransparente)
        btn22.text = ""
        btn22.setBackgroundResource(R.color.colorTransparente)
        btn23.text = ""
        btn23.setBackgroundResource(R.color.colorTransparente)
        btn31.text = ""
        btn31.setBackgroundResource(R.color.colorTransparente)
        btn32.text = ""
        btn32.setBackgroundResource(R.color.colorTransparente)
        btn33.text = ""
        btn33.setBackgroundResource(R.color.colorTransparente)
        txtAbajo.text = ""
        fin = false
        turno = (0..1).random()
        if (turno == 1) {
            txtArriba.text = getText(turnoUser)
            CoroutineScope(Dispatchers.Main).launch {
                //Thread.sleep(3500)
                delay(350)
                jugadadaOrd()
                ganarXO()
                turno = 0
                clickable = true
                clickar(clickable)
                txtArriba.text = getText(turnoOrd)
            }
        } else {
            clickable = true
            clickar(clickable)
            txtArriba.text = getText(turnoOrd)
        }
        partidas++
        Log.i("myapp", partidas.toString())
    }

    fun PartidaOrd(btn: Button) {
        btn.setOnClickListener {
            if (!fin && btn.text == "" && turno == 0) {
                btn.text = letraUser
                btn.setBackgroundResource(drawUser)
                turno = 1
                clickable = false
                clickar(clickable)
                txtArriba.text = getText(turnoUser)
                ganarXO()
            }
            CoroutineScope(Dispatchers.Main).launch {
                if (!fin && turno == 1) {
                    //Thread.sleep(3500)
                    delay(350)
                    jugadaOrd()
                    turno = 0
                    clickable = true
                    clickar(clickable)
                    txtArriba.text = getText(turnoOrd)
                    ganarXO()
                }
            }
        }
    }

    fun clickar(enable: Boolean) {
        btn11.isClickable = enable
        btn12.isClickable = enable
        btn13.isClickable = enable
        btn21.isClickable = enable
        btn22.isClickable = enable
        btn23.isClickable = enable
        btn31.isClickable = enable
        btn32.isClickable = enable
        btn33.isClickable = enable
    }

    fun jugadaUser(btn: Button) {
        btn.setOnClickListener {
            if (!fin && btn.text == "" && turno == 0) {
                btn.text = letraUser
                btn.setBackgroundResource(drawUser)
                turno = 1
                ganarXO()
            }
        }
    }

    fun jugadadaOrd() {
        if (!fin && turno == 1) {
            //Thread.sleep(3500)
            jugadaOrd()
            ganarXO()
            turno = 0
        }
    }

    fun jugadaOrd() {
        var validord = 0

        if (difi > 2) {
            if (btn22.text == "") {
                btn22.text = letraOrd
                btn22.setBackgroundResource(drawOrd)
                validord = 1
            }
            // Ataque diagonal -45
            else if (btn11.text == letraOrd && btn22.text == letraOrd && btn33.text == ""
            ) {
                btn33.text = letraOrd
                btn33.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraOrd && btn33.text == letraOrd && btn11.text == ""
            ) {
                btn11.text = letraOrd
                btn11.setBackgroundResource(drawOrd)
                validord = 1
            } //21 de 24 A
            // Ataque diagonal +45
            else if (btn13.text == letraOrd && btn22.text == letraOrd && btn31.text == ""
            ) {
                btn31.text = letraOrd
                btn31.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraOrd && btn31.text == letraOrd && btn13.text == ""
            ) {
                btn13.text = letraOrd
                btn13.setBackgroundResource(drawOrd)
                validord = 1
            } //24 de 24 final del ataque

            // Defensa diagonal -45
            else if (btn11.text == letraUser && btn22.text == letraUser && btn33.text == ""
            ) {
                btn33.text = letraOrd
                btn33.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraUser && btn33.text == letraUser && btn11.text == ""
            ) {
                btn11.text = letraOrd
                btn11.setBackgroundResource(drawOrd)
                validord = 1
            } //21 de 24 D
            // Defensa diagonal +45
            else if (btn13.text == letraUser && btn22.text == letraUser && btn31.text == ""
            ) {
                btn31.text = letraOrd
                btn31.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraUser && btn31.text == letraUser && btn13.text == ""
            ) {
                btn13.text = letraOrd
                btn13.setBackgroundResource(drawOrd)
                validord = 1
            }
            //24 de 24 final de la defensa
        }

        if (difi > 1 && validord == 0) {
            // Ataque central
            if (btn22.text == "") {
                btn22.text = letraOrd
                btn22.setBackgroundResource(drawOrd)
                validord = 1
            }
            // Ataque vertical hueco 3
            else if (btn11.text == letraOrd && btn21.text == letraOrd && btn31.text == ""
            ) {
                btn31.text = letraOrd
                btn31.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn12.text == letraOrd && btn22.text == letraOrd && btn32.text == ""
            ) {
                btn32.text = letraOrd
                btn32.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn13.text == letraOrd && btn23.text == letraOrd && btn33.text == ""
            ) {
                btn33.text = letraOrd
                btn33.setBackgroundResource(drawOrd)
                validord = 1
            } //12 de 24 A
            // Ataque vertical hueco 2
            else if (btn11.text == letraOrd && btn31.text == letraOrd && btn21.text == ""
            ) {
                btn21.text = letraOrd
                btn21.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn13.text == letraOrd && btn33.text == letraOrd && btn23.text == ""
            ) {
                btn23.text = letraOrd
                btn23.setBackgroundResource(drawOrd)
                validord = 1
            } //15 de 24 A
            // Ataque vertical hueco 1
            else if (btn21.text == letraOrd && btn31.text == letraOrd && btn11.text == ""
            ) {
                btn11.text = letraOrd
                btn11.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraOrd && btn32.text == letraOrd && btn12.text == ""
            ) {
                btn12.text = letraOrd
                btn12.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn23.text == letraOrd && btn33.text == letraOrd && btn13.text == ""
            ) {
                btn13.text = letraOrd
                btn13.setBackgroundResource(drawOrd)
                validord = 1
            } //18 de 24 A

            // Defensa vertical hueco 3
            else if (btn11.text == letraUser && btn21.text == letraUser && btn31.text == ""
            ) {
                btn31.text = letraOrd
                btn31.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn12.text == letraUser && btn22.text == letraUser && btn32.text == ""
            ) {
                btn32.text = letraOrd
                btn32.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn13.text == letraUser && btn23.text == letraUser && btn33.text == ""
            ) {
                btn33.text = letraOrd
                btn33.setBackgroundResource(drawOrd)
                validord = 1
            } //12 de 24 D
            // Defensa vertical hueco 2
            else if (btn11.text == letraUser && btn31.text == letraUser && btn21.text == ""
            ) {
                btn21.text = letraOrd
                btn21.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn13.text == letraUser && btn33.text == letraUser && btn23.text == ""
            ) {
                btn23.text = letraOrd
                btn23.setBackgroundResource(drawOrd)
                validord = 1
            } //15 de 24 D
            // Defensa vertical hueco 1
            else if (btn21.text == letraUser && btn31.text == letraUser && btn11.text == ""
            ) {
                btn11.text = letraOrd
                btn11.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraUser && btn32.text == letraUser && btn12.text == ""
            ) {
                btn12.text = letraOrd
                btn12.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn23.text == letraUser && btn33.text == letraUser && btn13.text == ""
            ) {
                btn13.text = letraOrd
                btn13.setBackgroundResource(drawOrd)
                validord = 1
            } //18 de 24 D
        }

        if (difi > 0 && validord == 0) {

            //Ataque horizontal hueco 3
            if (btn11.text == letraOrd && btn12.text == letraOrd && btn13.text == "") {
                btn13.text = letraOrd
                btn13.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn21.text == letraOrd && btn22.text == letraOrd && btn23.text == ""
            ) {
                btn23.text = letraOrd
                btn23.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn31.text == letraOrd && btn32.text == letraOrd && btn33.text == ""
            ) {
                btn33.text = letraOrd
                btn33.setBackgroundResource(drawOrd)
                validord = 1
            } //3 de 24 A
            // Ataque horizontal hueco 2
            else if (btn11.text == letraOrd && btn13.text == letraOrd && btn12.text == ""
            ) {
                btn12.text = letraOrd
                btn12.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn31.text == letraOrd && btn33.text == letraOrd && btn32.text == ""
            ) {
                btn32.text = letraOrd
                btn32.setBackgroundResource(drawOrd)
                validord = 1
            } //6 de 24 A
            // Ataque horizontal hueco 1
            else if (btn12.text == letraOrd && btn13.text == letraOrd && btn11.text == ""
            ) {
                btn11.text = letraOrd
                btn11.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraOrd && btn23.text == letraOrd && btn21.text == ""
            ) {
                btn21.text = letraOrd
                btn21.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn32.text == letraOrd && btn33.text == letraOrd && btn31.text == ""
            ) {
                btn31.text = letraOrd
                btn31.setBackgroundResource(drawOrd)
                validord = 1
            } //9 de 24 A

            // Defensa horizontal hueco 3
            else if (btn11.text == letraUser && btn12.text == letraUser && btn13.text == ""
            ) {
                btn13.text = letraOrd
                btn13.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn21.text == letraUser && btn22.text == letraUser && btn23.text == ""
            ) {
                btn23.text = letraOrd
                btn23.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn31.text == letraUser && btn32.text == letraUser && btn33.text == ""
            ) {
                btn33.text = letraOrd
                btn33.setBackgroundResource(drawOrd)
                validord = 1
            } //3 de 24 D
            // Defensa horizontal hueco 2
            else if (btn11.text == letraUser && btn13.text == letraUser && btn12.text == ""
            ) {
                btn12.text = letraOrd
                btn12.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn31.text == letraUser && btn33.text == letraUser && btn32.text == ""
            ) {
                btn32.text = letraOrd
                btn32.setBackgroundResource(drawOrd)
                validord = 1
            } //6 de 24 D
            // Defensa horizontal hueco 1
            else if (btn12.text == letraUser && btn13.text == letraUser && btn11.text == ""
            ) {
                btn11.text = letraOrd
                btn11.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn22.text == letraUser && btn23.text == letraUser && btn21.text == ""
            ) {
                btn21.text = letraOrd
                btn21.setBackgroundResource(drawOrd)
                validord = 1
            } else if (btn32.text == letraUser && btn33.text == letraUser && btn31.text == ""
            ) {
                btn31.text = letraOrd
                btn31.setBackgroundResource(drawOrd)
                validord = 1
            } //9 de 24 D
        }

        if (validord == 0) {
            do {
                //Elijo aleatoriamente
                val ale: String = (1..3).random().toString() + (1..3).random().toString()
                if (ale == "11" && btn11.text == "") {
                    btn11.text = letraOrd
                    btn11.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "12" && btn12.text == "") {
                    btn12.text = letraOrd
                    btn12.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "13" && btn13.text == "") {
                    btn13.text = letraOrd
                    btn13.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "21" && btn21.text == "") {
                    btn21.text = letraOrd
                    btn21.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "22" && btn22.text == "") {
                    btn22.text = letraOrd
                    btn22.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "23" && btn23.text == "") {
                    btn23.text = letraOrd
                    btn23.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "31" && btn31.text == "") {
                    btn31.text = letraOrd
                    btn31.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "32" && btn32.text == "") {
                    btn32.text = letraOrd
                    btn32.setBackgroundResource(drawOrd)
                    validord = 1
                } else if (ale == "33" && btn33.text == "") {
                    btn33.text = letraOrd
                    btn33.setBackgroundResource(drawOrd)
                    validord = 1
                } else {
                    validord = 0
                }
            } while (validord == 0)
        }
    }

    fun ganarXO() {
        if (btn11.text == "X" && btn12.text == "X" && btn13.text == "X" ||
            btn21.text == "X" && btn22.text == "X" && btn23.text == "X" ||
            btn31.text == "X" && btn32.text == "X" && btn33.text == "X" ||
            btn11.text == "X" && btn21.text == "X" && btn31.text == "X" ||
            btn12.text == "X" && btn22.text == "X" && btn32.text == "X" ||
            btn13.text == "X" && btn23.text == "X" && btn33.text == "X" ||
            btn11.text == "X" && btn22.text == "X" && btn33.text == "X" ||
            btn31.text == "X" && btn22.text == "X" && btn13.text == "X"
        ) {
            txtAbajo.text = getText(ganaOrd)
            txtArriba.text = getText(ganaOrd)

            fin = true
        } else if (btn11.text == "O" && btn12.text == "O" && btn13.text == "O" ||
            btn21.text == "O" && btn22.text == "O" && btn23.text == "O" ||
            btn31.text == "O" && btn32.text == "O" && btn33.text == "O" ||
            btn11.text == "O" && btn21.text == "O" && btn31.text == "O" ||
            btn12.text == "O" && btn22.text == "O" && btn32.text == "O" ||
            btn13.text == "O" && btn23.text == "O" && btn33.text == "O" ||
            btn11.text == "O" && btn22.text == "O" && btn33.text == "O" ||
            btn31.text == "O" && btn22.text == "O" && btn13.text == "O"
        ) {
            txtAbajo.text = getText(ganaUser)
            txtArriba.text = getText(ganaUser)
            fin = true
        } else if (btn11.text != "" && btn12.text != "" && btn13.text != "" &&
            btn21.text != "" && btn22.text != "" && btn23.text != "" &&
            btn31.text != "" && btn32.text != "" && btn33.text != ""
        ) {
            txtAbajo.text = getText(R.string.Empate)
            txtArriba.text = getText(R.string.Empate)
            fin = true
        }
    }
}