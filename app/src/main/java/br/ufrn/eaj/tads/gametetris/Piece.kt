package br.ufrn.eaj.tads.gametetris

abstract class Piece(var x: Int, var y: Int) {

    var pontoA: Ponto = Ponto(x, y)
    lateinit var pontoB: Ponto
    lateinit var pontoC: Ponto
    lateinit var pontoD: Ponto


    abstract fun moverBaixo()
    abstract fun moverEsquerda()
    abstract fun moverDireita()
    abstract fun girar()

}