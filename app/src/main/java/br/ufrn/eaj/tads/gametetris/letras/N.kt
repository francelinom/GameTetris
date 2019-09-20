package br.ufrn.eaj.tads.gametetris.letras

import br.ufrn.eaj.tads.gametetris.Piece
import br.ufrn.eaj.tads.gametetris.Ponto

class N(x: Int, y: Int) : Piece(x, y) {

    var girar = 0

    init {
        pontoB = Ponto(x - 1, y + 1)
        pontoC = Ponto(x, y + 1)
        pontoD = Ponto(x + 1, y)

    }

    override fun moverBaixo() {
        pontoA.moverBaixo()
        pontoB.moverBaixo()
        pontoC.moverBaixo()
        pontoD.moverBaixo()
    }

    override fun moverEsquerda() {
        pontoA.moverEsquerda()
        pontoB.moverEsquerda()
        pontoC.moverEsquerda()
        pontoD.moverEsquerda()
    }

    override fun moverDireita() {
        pontoA.moverDireita()
        pontoB.moverDireita()
        pontoC.moverDireita()
        pontoD.moverDireita()
    }

    fun moverCima() {
        pontoA.moverCima()
        pontoB.moverCima()
        pontoC.moverCima()
        pontoD.moverCima()
    }

    override fun girar() {

        if (girar == 0){
            pontoB.x += 2
            pontoB.y += 0

            pontoC.x += 1
            pontoC.y -= 1

            pontoD.x -= 1
            pontoD.y -= 1

            girar = 1

        }else{
            pontoB.x -= 2
            pontoB.y -= 0

            pontoC.x -= 1
            pontoC.y += 1

            pontoD.x += 1
            pontoD.y += 1

            girar = 0
        }



    }
}