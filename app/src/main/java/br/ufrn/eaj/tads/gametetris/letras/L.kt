package br.ufrn.eaj.tads.gametetris.letras

import br.ufrn.eaj.tads.gametetris.Piece
import br.ufrn.eaj.tads.gametetris.Ponto

class L(x:Int, y:Int) : Piece(x, y) {

    var girar = 0

    init {
        pontoB = Ponto(x - 1, y)
        pontoC = Ponto(x - 2, y)
        pontoD = Ponto(x, y + 1)
    }

    override fun moverBaixo(){
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

    fun moverCima(){
        pontoA.moverCima()
        pontoB.moverCima()
        pontoC.moverCima()
        pontoD.moverCima()
    }

    override fun girar() {

        if (girar == 0){
            pontoB.x += 1
            pontoB.y += 1

            pontoC.x += 2
            pontoC.y += 2

            pontoD.x += 1
            pontoD.y -= 1

            girar = 1

        }else{
            pontoB.x -= 1
            pontoB.y -= 1

            pontoC.x -= 2
            pontoC.y -= 2

            pontoD.x -= 1
            pontoD.y += 1

            girar = 0
        }

    }
}