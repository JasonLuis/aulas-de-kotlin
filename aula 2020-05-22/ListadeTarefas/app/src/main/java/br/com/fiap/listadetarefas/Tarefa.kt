package br.com.fiap.listadetarefas

data class Tarefa (
    val descricao: String,
    var concluida: Boolean,
    val idTarefa:Int? = null,

    val onUpdate: ((Tarefa) ->Unit)? = null
)

