package br.com.fiap.listadetarefas.database

import android.content.ContentValues
import br.com.fiap.listadetarefas.Tarefa

//this.writableDatabase: Banco de dados escrita: update, delete, insert
//this.readableDatabase: Bandco de dados leitura: select

fun TarefasDatabase.listarTarefas(): List<Tarefa> {

   val sql = "SELECT * FROM TBL_TAREFAS"

   val cursor = this.readableDatabase.rawQuery(sql,null)

    val resultados = mutableListOf<Tarefa>()

    while (cursor.moveToNext()){

        val id = cursor.getInt(cursor.getColumnIndex("ID"))
        val descricao = cursor.getString(cursor.getColumnIndex("DESCRICAO"))
        val flagConcluida = cursor.getInt(cursor.getColumnIndex("FLAG_CONCLUIDA"))

        resultados.add(
            Tarefa(
                descricao = descricao,
                concluida = flagConcluida == 1,
                idTarefa = id
            )
        )
    }
    cursor.close()

    return resultados
}

fun TarefasDatabase.inserirTarefa(item: Tarefa): Int{

    val id = this.writableDatabase.insert("TBL_TAREFAS",null, ContentValues().apply {
        put("DESCRICAO",item.descricao)
        put("FLAG_CONCLUIDA",if(item.concluida) 1 else 0)
    })

    return id.toInt()
}

fun TarefasDatabase.atualizarTarefa(item: Tarefa): Int{

    val valores = ContentValues().apply {
        put("FLAG_CONCLUIDA", if(item.concluida) 1 else 0)
    }
    //update TBL_TAREFAS set FLAG_CONCLUIDA = $concluida WHERE ID = ${item.idTarefa}
   return this.writableDatabase.update("TBL_TAREFAS",valores, "ID=${item.idTarefa}",null)
}

fun TarefasDatabase.removerTarefa(idTarefa:Int?): Int{
    //DELETE FROM TBL_TAREFAS WHERE ID = $idTarefa
    return this.writableDatabase.delete("TBL_TAREFAS","ID=${idTarefa}", null)
}