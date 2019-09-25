package br.ufrn.eaj.tads.gametetris

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {


    val PREFS = "prefs_file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        listener()
        var params = intent.extras
        var novosPontos = params?.getString("pontuacaoAtual")

        textViewPontuacao.text = novosPontos.toString()


        val setting = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        var edit = setting.edit()


        var recordAtual = setting.getInt("recordAtual", 0)

        textViewRecordAtual.text = Integer.toString(recordAtual)

        var pontuacaoAtual = Integer.parseInt(textViewPontuacao.text.toString())

        if(pontuacaoAtual > recordAtual){
            edit.putInt("recordAtual", pontuacaoAtual)
            edit.commit()

            textViewNovoR.visibility = View.VISIBLE


        }
    }

    fun listener(){
        buttonNovoJogo.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        buttonSair.setOnClickListener {
            finish()
        }
    }
}
