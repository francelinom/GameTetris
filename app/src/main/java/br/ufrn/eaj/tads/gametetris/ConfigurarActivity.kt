package br.ufrn.eaj.tads.gametetris

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_configurar.*

class ConfigurarActivity : AppCompatActivity() {

    val PREFS = "prefs_file"

    var speed: Long = 300


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurar)

        radioButtonFacil.setOnClickListener {
            speed = 450
        }

        radioButtonNormal.setOnClickListener {
            speed = 300
        }

        radioButtonDificil.setOnClickListener {
            speed = 100
        }

        buttonSalvar.setOnClickListener {
            save()
            finish()
        }
    }

    fun save(){
        val salvar = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        var editor = salvar.edit()

        editor.putLong("speed", speed)
        editor.commit()
    }
}
