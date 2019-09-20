package br.ufrn.eaj.tads.gametetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import br.ufrn.eaj.tads.gametetris.letras.*


class MainActivity : AppCompatActivity() {

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed: Long = 300

    var pt = T(3, 15)

    var board = Array(LINHA) {
        Array(COLUNA) { 0 }
    }

    var boardView = Array(LINHA) {
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridboard.rowCount = LINHA
        gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)

        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] =
                    inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView(boardView[i][j])
            }
        }



        buttonEsquerda.setOnClickListener {
            pt.moverEsquerda()
        }

        buttonDireita.setOnClickListener {
            pt.moverDireita()
        }

        buttonGirar.setOnClickListener {
            pt.girar()
        }

        buttonDescer.setOnClickListener {
            pt.moverBaixo()
        }

        try {
            boardView[pt.pontoA.x][pt.pontoA.y]!!.setImageResource(R.drawable.white)
            boardView[pt.pontoB.x][pt.pontoB.y]!!.setImageResource(R.drawable.white)
            boardView[pt.pontoC.x][pt.pontoC.y]!!.setImageResource(R.drawable.white)
            boardView[pt.pontoD.x][pt.pontoD.y]!!.setImageResource(R.drawable.white)
        } catch (e: ArrayIndexOutOfBoundsException) {
            running = false
        }


        gameRun()
    }


    fun gameRun() {
        Thread {
            while (running) {
                Thread.sleep(speed)
                runOnUiThread {
                    //limpa tela
                    for (i in 0 until LINHA) {
                        for (j in 0 until COLUNA) {

                            if (board[i][j] == 0) {
                                boardView[i][j]!!.setImageResource(R.drawable.black)
                            }
                        }
                    }
                    //move peça atual
                    //pt.moverBaixo()
                    try {
                        ConstruirPeca()
                        if (pt.pontoA.x + 1 < LINHA || pt.pontoB.x + 1 < LINHA || pt.pontoC.x + 1 < LINHA || pt.pontoD.x + 1 < LINHA) {
                            pt.moverBaixo()
                        } else {

                            atualizarPeca()

                        }

                    } catch (e: ArrayIndexOutOfBoundsException) {
                        //se a peça passou das bordas eu vou parar o jogo
                        //running = false
                        println(e.message)
                    }


                }
            }
        }.start()
    }


    fun atualizarPeca() {
        board[pt.pontoA.x - 1][pt.pontoA.y] = 1
        board[pt.pontoB.x - 1][pt.pontoB.y] = 1
        board[pt.pontoC.x - 1][pt.pontoC.y] = 1
        board[pt.pontoD.x - 1][pt.pontoD.y] = 1

        novaPeca()
        ConstruirPeca()

    }

    fun ConstruirPeca() {

        boardView[pt.pontoA.x][pt.pontoA.y]!!.setImageResource(R.drawable.white)
        boardView[pt.pontoB.x][pt.pontoB.y]!!.setImageResource(R.drawable.white)
        boardView[pt.pontoC.x][pt.pontoC.y]!!.setImageResource(R.drawable.white)
        boardView[pt.pontoD.x][pt.pontoD.y]!!.setImageResource(R.drawable.white)

    }

    fun novaPeca() {
        pt = T(3, 15)

    }


}
