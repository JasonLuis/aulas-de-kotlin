package br.com.fiap.listadetarefas

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val textViewTarefa = view.findViewById<TextView>(R.id.textViewTarefa)
    private val checkBoxTarefa = view.findViewById<CheckBox>(R.id.checkboxTarefa)

    fun bind(item: Tarefa){

        checkBoxTarefa.setOnCheckedChangeListener{ _, isChecked ->
            item.concluida = isChecked

            //chamar função de update
            item.onUpdate?.invoke(item)
        }

        textViewTarefa.text = item.descricao
        checkBoxTarefa.isChecked =  item.concluida
    }
}