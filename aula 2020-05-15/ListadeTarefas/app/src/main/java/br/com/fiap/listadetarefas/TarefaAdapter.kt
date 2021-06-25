package br.com.fiap.listadetarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter: RecyclerView.Adapter<TarefaViewHolder> (){

    private val items = mutableListOf<Tarefa>()

    fun addItem(newItem: Tarefa) {
        items.add(newItem)

        //notificar que houve mudanças na lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return  TarefaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }



}