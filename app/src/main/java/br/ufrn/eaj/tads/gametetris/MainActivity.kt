package br.ufrn.eaj.tads.gametetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import br.ufrn.eaj.tads.gametetris.letras.*
import kotlin.random.Random
import kotlin.random.nextInt


class MainActivity : AppCompatActivity() {

    open class Viewmodel : ViewModel(){
        val LINHA = 36
        val COLUNA = 26

        var board = Array(LINHA) {
            Array(COLUNA) { 0 }
        }
    }

    val vm : Viewmodel by lazy {
        ViewModelProviders.of(this)[Viewmodel::class.java]
    }

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed: Long = 300
    var pontos = 0

    var pt: Piece = pecasVariadas() //T(3, 15)


   /* var board = Array(LINHA) {
        Array(COLUNA) { 0 }
    }*/

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
            if (colisaoEsquerda()) {
                pt.moverEsquerda()
            }
        }

        buttonDireita.setOnClickListener {
            if (colisaoDireita()) {
                pt.moverDireita()
            }
        }

        buttonGirar.setOnClickListener {
            if (colisaoEsquerda() && colisaoDireita()){
                pt.girar()
            }
           // pt.girar()  ESSA PARTE FOI PARA DENTRO DO IF
        }

        buttonDescer.setOnClickListener {
            if (colisaoFundo()) {
                pt.moverBaixo()
            }
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

                            when (vm.board[i][j]) {
                                0 -> {
                                    boardView[i][j]!!.setImageResource(R.drawable.black)
                                }
                                1 -> {
                                    boardView[i][j]!!.setImageResource(R.drawable.white)
                                }
                            }
                            /*
                            if (board[i][j] == 0) {
                                boardView[i][j]!!.setImageResource(R.drawable.black)
                            }*/
                        }
                    }

                    //move peça atual

                    if (colisaoFundo()) {
                        pt.moverBaixo()
                        ConstruirPeca()
                    } else {
                        ConstruirPeca()
                        vm.board[pt.pontoA.x][pt.pontoA.y] = 1
                        vm.board[pt.pontoB.x][pt.pontoB.y] = 1
                        vm.board[pt.pontoC.x][pt.pontoC.y] = 1
                        vm.board[pt.pontoD.x][pt.pontoD.y] = 1
                        pt = pecasVariadas()
                    }

                    for (i in 0 until LINHA) {
                        var cont = 0
                        for (j in 0 until COLUNA) {
                            if (vm.board[i][j] == 0)
                                break
                            else {
                                cont++
                                if (cont === 20) {
                                    destruir(i)
                                }
                            }

                        }
                    }

                    /*


                    if (pt.pontoA.x <= LINHA && pt.pontoB.x + 0 <= LINHA && pt.pontoC.x <= LINHA && pt.pontoD.x <= LINHA) {
                        if (board[pt.pontoA.x + 1][pt.pontoA.y] == 0 &&
                            board[pt.pontoB.x + 1][pt.pontoB.y] == 0 &&
                            board[pt.pontoC.x + 1][pt.pontoC.y] == 0 &&
                            board[pt.pontoD.x + 1][pt.pontoD.y] == 0
                        ) {
                            pt.moverBaixo()
                        }

                    } else {

                        atualizarPeca()

                    }


                    try {
                        ConstruirPeca()

                    } catch (e: ArrayIndexOutOfBoundsException) {
                        //se a peça passou das bordas eu vou parar o jogo
                        //running = false
                        println(e.message)
                    }*/
                }
            }
        }.start()
    }


    fun atualizarPeca() {
        vm.board[pt.pontoA.x - 1][pt.pontoA.y] = 1
        vm.board[pt.pontoB.x - 1][pt.pontoB.y] = 1
        vm.board[pt.pontoC.x - 1][pt.pontoC.y] = 1
        vm.board[pt.pontoD.x - 1][pt.pontoD.y] = 1

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

    fun pecasVariadas(): Piece {

        var todasPecas = Random.nextInt(0, 6)

        return when (todasPecas) {
            0 -> {
                return L(3, 15)
            }
            1 -> {
                return I(3, 15)
            }
            2 -> {
                return J(3, 15)
            }
            3 -> {
                return N(3, 15)
            }
            4 -> {
                return O(3, 15)
            }
            5 -> {
                return S(3, 15)
            }
            else -> {
                return T(3, 15)
            }
        }
    }

    fun colisaoFundo(): Boolean {
        return ((pt.pontoA.x + 1 < LINHA && vm.board[pt.pontoA.x + 1][pt.pontoA.y] < 1) &&
                (pt.pontoB.x + 1 < LINHA && vm.board[pt.pontoB.x + 1][pt.pontoB.y] < 1) &&
                (pt.pontoC.x + 1 < LINHA && vm.board[pt.pontoB.x + 1][pt.pontoC.y] < 1) &&
                (pt.pontoD.x + 1 < LINHA && vm.board[pt.pontoD.x + 1][pt.pontoD.y] < 1))
    }

    fun colisaoDireita(): Boolean {
        return ((pt.pontoA.y + 1 < COLUNA && vm.board[pt.pontoA.x][pt.pontoA.y + 1] < 1) &&
                (pt.pontoB.y + 1 < COLUNA && vm.board[pt.pontoB.x][pt.pontoB.y + 1] < 1) &&
                (pt.pontoC.y + 1 < COLUNA && vm.board[pt.pontoB.x][pt.pontoC.y + 1] < 1) &&
                (pt.pontoD.y + 1 < COLUNA && vm.board[pt.pontoD.x][pt.pontoD.y] < 1))

    }

    fun colisaoEsquerda(): Boolean {
        return ((pt.pontoA.y - 1 >= 0 && vm.board[pt.pontoA.x][pt.pontoA.y - 1] < 1) &&
                (pt.pontoB.y - 1 >= 0 && vm.board[pt.pontoB.x][pt.pontoB.y - 1] < 1) &&
                (pt.pontoC.y - 1 >= 0 && vm.board[pt.pontoB.x][pt.pontoC.y - 1] < 1) &&
                (pt.pontoD.y - 1 >= 0 && vm.board[pt.pontoD.x][pt.pontoD.y - 1] < 1))

    }

    fun destruir(linha: Int) {
        vm.board[linha] = Array(COLUNA) { 0 }
        for (i in linha downTo 1) {
            vm.board[i] = vm.board[i - 1]
        }
        pontos += COLUNA
        textResult.text = "$pontos" //(vai receber os pontos das destruições)
    }

    override fun onPause(){
        super.onPause()
        running = false
    }

    override fun onRestart() {
        super.onRestart()
        running = true
        gameRun()
    }


}
