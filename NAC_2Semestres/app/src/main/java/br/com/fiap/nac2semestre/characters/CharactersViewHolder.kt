package br.com.fiap.nac2semestre.characters
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.nac2semestre.characters.entidadesApi.Characters
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*



class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(item: Characters, action: OnClickItem){
        itemView.textViewIdHeroi.text = item.modified.toString()
        itemView.textViewIdHeroi.text = "ID do Personagem: "+ item.id.toString()
        itemView.textViewNomeHeroi.text = item.name
        var imagemFile = item.thumbnail!!.path+"."+item.thumbnail!!.extension
        Picasso.get().load(imagemFile).into(itemView.imageViewHeroi)

        itemView.setOnClickListener{
            action.onItemClick(item, adapterPosition)
        }
    }
}

interface OnClickItem{
    fun onItemClick(item: Characters, position: Int)
}