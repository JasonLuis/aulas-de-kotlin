package br.com.fiap.listadetarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.fiap.listadetarefas.Tarefa

class TarefasDatabase(
    context: Context
): SQLiteOpenHelper(context, "tarefas.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
             CREATE TABLE TBL_TAREFAS(
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                DESCRICAO TEXT,
                FLAG_CONCLUIDA INTEGER
             );  
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, new : Int) {
        db?.execSQL("DROP TABLE IF EXISTS TBL_TAREFAS")
        onCreate(db)
    }



}
