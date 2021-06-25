package br.com.fiap.a3sia_firebase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bind(item: User){
        itemView.textViewName.setText(item.name)
        itemView.textViewPhone.setText(item.phone)
    }
}