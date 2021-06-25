package br.com.fiap.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import br.com.fiap.listadetarefas.database.TarefasDatabase
import br.com.fiap.listadetarefas.database.atualizarTarefa
import br.com.fiap.listadetarefas.database.inserirTarefa
import br.com.fiap.listadetarefas.database.listarTarefas

class MainActivity : AppCompatActivity() {

    lateinit var editTextTarefa: EditText
    lateinit var buttonAdicionar: Button
    lateinit var recyclerView: RecyclerView
    lateinit var database: TarefasDatabase

    val tarefaAdapter = TarefaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTarefa = findViewById(R.id.editTextTarefa)
        buttonAdicionar = findViewById(R.id.buttonAdicionar)
        recyclerView = findViewById(R.id.recycleView)

        database = TarefasDatabase(this)

        recyclerView.adapter = tarefaAdapter
        //recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //listar tarefas do banco
        database.listarTarefas().forEach{
            tarefaAdapter.addItem(it.copy (
                onUpdate = ::atualizarTarefa
            ))
        }

        buttonAdicionar.setOnClickListener{

            val tarefa = Tarefa(
                descricao =  editTextTarefa.text.toString(),
                concluida = false,
                onUpdate =::atualizarTarefa
            )

            val idTarefa = database.inserirTarefa(tarefa)
            if(idTarefa != -1){
               tarefaAdapter.addItem(tarefa.copy(idTarefa = idTarefa))
            }else{
               Toast.makeText(this, "Erro ao inserir no banco", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun atualizarTarefa(item: Tarefa){
        database.atualizarTarefa(item)
    }
}