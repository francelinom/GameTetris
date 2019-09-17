package br.ufrn.eaj.tads.gametetris

class N (x:Int, y:Int) : Piece(x, y){

    init {
        pontoB = Ponto(x-1, y+1)
        pontoC = Ponto(x, y+1)
        pontoD = Ponto(x+1, y)

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
        pontoB.x += 1
        pontoB.y += 1

        pontoC.x += 2
        pontoC.y += 2

        pontoD.x += 1
        pontoD.y -= 1
    }
}