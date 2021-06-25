package br.com.fiap.nac2semestre

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.nac2semestre.characters.CharactersAdapter
import br.com.fiap.nac2semestre.characters.CharactersViewModel
import br.com.fiap.nac2semestre.characters.OnClickItem
import br.com.fiap.nac2semestre.characters.entidadesApi.Characters
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnClickItem {

    private lateinit var recyclerView: RecyclerView

    val heroiAdapter = CharactersAdapter(this)
    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = heroiAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        observeLiveDatas()
    }

    private fun observeLiveDatas(){
        viewModel.heroiLiveData.observe(this, Observer {
            heroiAdapter.updateItems(it)
        })
    }

    override fun onItemClick(item: Characters, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("NAME", item.name)
        intent.putExtra("STORIES",item.series!!.items.toString())
        intent.putExtra("IMAGE", item.thumbnail!!.path + "." + item.thumbnail!!.extension)
        startActivity(intent)
    }


}



