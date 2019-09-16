package br.ufrn.eaj.tads.gametetris

class Ponto(var x:Int,var y:Int) {

    fun moverBaixo(){
        x++
    }

    fun moverEsquerda(){
        y--
    }

    fun moverDireita(){
        y--
    }
}