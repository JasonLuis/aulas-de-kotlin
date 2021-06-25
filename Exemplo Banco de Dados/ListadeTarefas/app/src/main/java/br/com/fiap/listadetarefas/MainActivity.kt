package br.com.fiap.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

class MainActivity : AppCompatActivity() {

    lateinit var editTextTarefa: EditText
    lateinit var buttonAdicionar: Button
    lateinit var recyclerView: RecyclerView

    val tarefaAdapter = TarefaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTarefa = findViewById(R.id.editTextTarefa)
        buttonAdicionar = findViewById(R.id.buttonAdicionar)
        recyclerView = findViewById(R.id.recycleView)

        recyclerView.adapter = tarefaAdapter
        //recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonAdicionar.setOnClickListener{

            val tarefa = Tarefa(
                descricao =  editTextTarefa.text.toString(),
                concluida = false
            )

            if(editTextTarefa.text.isEmpty()){
              Toast.makeText(baseContext, "Digite alguma tarefa",Toast.LENGTH_SHORT).show()
            }else{
              tarefaAdapter.addItem(tarefa)
            }


        }

    }

}