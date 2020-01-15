package com.example.virtujuegos.juegos
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.example.virtujuegos.MenuJuegos
import com.example.virtujuegos.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_emparejar.*


class EmparejarActivity : AppCompatActivity() {
    var codigo : ArrayList<Int>  = arrayListOf(1,2, 3, 4, 1,2, 3, 4)
    var contador = 0
    lateinit var listaBotones: ArrayList<CircleImageView>
    lateinit var listaBotones2: ArrayList<CircleImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_emparejar)
        textBox.typeface = Typeface.createFromAsset(assets, "fonts/CURSHT.TTF")
        btnReplayParejas.setOnClickListener {
            replay()
        }
        codigo.shuffle()
        listaBotones2 = arrayListOf(btnPareja3)
        listaBotones2.clear()
        listaBotones = arrayListOf(
            btnPareja1,
            btnPareja2,
            btnPareja3,
            btnPareja4,
            btnPareja5,
            btnPareja6,
            btnPareja7,
            btnPareja8
        )
        jugar(btnPareja1)
        jugar(btnPareja2)
        jugar(btnPareja3)
        jugar(btnPareja4)
        jugar(btnPareja5)
        jugar(btnPareja6)
        jugar(btnPareja7)
        jugar(btnPareja8)
    }
    fun replay(){
        codigo.shuffle()
        contador = 0
        for (i in 0 until  listaBotones.size){
            listaBotones[i].setBackgroundResource(R.drawable.btn_carta)
            listaBotones[i].isClickable= true
        }
        textBox.text = getString(R.string.Busca_las_parejas)
    }
    fun jugar(btn: CircleImageView) {
        btn.setOnClickListener {
            listaBotones2.add(btn)
            for (i in 0 until listaBotones.size){
                if(listaBotones[i] == btn){
                    if(codigo[i]==1){
                        btn.setBackgroundResource(R.drawable.ic_volume)
                        btn.tag = 1
                    }else if (codigo[i]==2){
                        btn.setBackgroundResource(R.drawable.ic_audiotrack)
                        btn.tag = 2
                    }else if (codigo[i]==3){
                        btn.setBackgroundResource(R.drawable.ic_person_white_24dp)
                        btn.tag = 3
                    }else if (codigo[i]==4){
                        btn.setBackgroundResource(R.drawable.ic_launcher_foreground)
                        btn.tag = 4
                    }
                }
            }
            Log.v("miapp", listaBotones2.size.toString())
            if (listaBotones2.size==2){
                if (listaBotones2[0].tag != listaBotones2[1].tag){
                    Handler().postDelayed({
                        listaBotones2[0].setBackgroundResource(R.drawable.btn_carta)
                        listaBotones2[1].setBackgroundResource(R.drawable.btn_carta)
                        listaBotones2.clear()
                    },500)
                } else{
                    listaBotones2[0].isClickable = false
                    listaBotones2[1].isClickable = false
                    listaBotones2.clear()
                    contador++
                    if (contador == 4 ){
                        val builder = AlertDialog.Builder(this)
                        // Set the alert dialog title
                        builder.setTitle("Enhorabuena has ganado!")
                        // Display a message on alert dialog
                        builder.setMessage("Quieres jugar otra partida?")
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
                            val intent = Intent(this, MenuJuegos::class.java)
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
        }
    }
}