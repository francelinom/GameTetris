package br.ufrn.eaj.tads.gametetris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tela_inicio.*

class TelaInicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicio)

        buttonIniciar.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        buttonConfigurar.setOnClickListener {
            var i = Intent(this, ConfigurarActivity::class.java)
            startActivity(i)
        }


    }
}
