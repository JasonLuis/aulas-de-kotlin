package br.com.fiap.nac2semestre.characters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.nac2semestre.R
import br.com.fiap.nac2semestre.characters.entidadesApi.Characters


class CharactersAdapter(var clickListner: OnClickItem): RecyclerView.Adapter<CharactersViewHolder>() {

    private  var items =mutableListOf<Characters>()
    fun updateItems(newItens: List<Characters>) {
        items = newItens.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(items[position],clickListner)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

