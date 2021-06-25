package br.com.fiap.nac2semestre.characters.entidadesApi

data class Items (
    val name:String? = null
){
    override fun toString():String{
        var aux:String
        aux = "$name, \n"
        return aux;
    }
}