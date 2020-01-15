package com.example.virtujuegos.juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.virtujuegos.R
import kotlinx.android.synthetic.main.activity_tres_dos.*

class TresDos : AppCompatActivity() {

    var turno = (0..1).random()
    var fin = false
    var partidas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tres_dos)

        btnBackUsers.setOnClickListener {
            val intent = Intent(this, TresActivity::class.java)
            startActivity(intent)
        }

        cuadro.layoutParams.height = windowManager.defaultDisplay.width
        RelArriba.layoutParams.height = windowManager.defaultDisplay.height/8


        if (partidas == 0) {
            if (turno == 0) {
                txtArriba.text = getText(R.string.Turno_Pop)
            }
            if (turno == 1) {
                txtArriba.text = getText(R.string.Turno_Lea)
            }
        }

        jugada(btn11)
        jugada(btn12)
        jugada(btn13)
        jugada(btn21)
        jugada(btn22)
        jugada(btn23)
        jugada(btn31)
        jugada(btn32)
        jugada(btn33)

        btnReplay.setOnClickListener {
            deleteTable()
        }
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
        if (turno == 0) {
            txtArriba.text = getText(R.string.Turno_Pop)
        }
        if (turno == 1) {
            txtArriba.text = getText(R.string.Turno_Lea)
        }
    }

    fun jugada(btn: Button) {
        btn.setOnClickListener {
            if (txtAbajo.text == "") {
                if (btn.text == "" && turno == 0) {
                    btn.text = "O"
                    btn.setBackgroundResource(R.drawable.ic_launcher_foreground)
                    turno = 1
                    txtArriba.text = getText(R.string.Turno_Lea)
                } else if (btn.text == "" && turno == 1) {
                    btn.text = "X"
                    btn.setBackgroundResource(R.drawable.ic_person_white_24dp)
                    turno = 0
                    txtArriba.text = getText(R.string.Turno_Pop)
                }
                ganarXO()
            }
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

            txtAbajo.text = getText(R.string.Gana_Lea)
            txtArriba.text = getText(R.string.Gana_Lea)

        } else if (btn11.text == "O" && btn12.text == "O" && btn13.text == "O" ||
            btn21.text == "O" && btn22.text == "O" && btn23.text == "O" ||
            btn31.text == "O" && btn32.text == "O" && btn33.text == "O" ||
            btn11.text == "O" && btn21.text == "O" && btn31.text == "O" ||
            btn12.text == "O" && btn22.text == "O" && btn32.text == "O" ||
            btn13.text == "O" && btn23.text == "O" && btn33.text == "O" ||
            btn11.text == "O" && btn22.text == "O" && btn33.text == "O" ||
            btn31.text == "O" && btn22.text == "O" && btn13.text == "O"
        ) {
            txtAbajo.text = getText(R.string.Gana_Pop)
            txtArriba.text = getText(R.string.Gana_Pop)
        } else if (btn11.text != "" && btn12.text != "" && btn13.text != "" &&
            btn21.text != "" && btn22.text != "" && btn23.text != "" &&
            btn31.text != "" && btn32.text != "" && btn33.text != ""
        ) {
            txtAbajo.text = getText(R.string.Empate)
            txtArriba.text = getText(R.string.Empate)
        }
    }
}
