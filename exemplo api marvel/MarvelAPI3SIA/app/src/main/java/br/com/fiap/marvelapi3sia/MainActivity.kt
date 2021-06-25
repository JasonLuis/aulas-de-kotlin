package br.com.fiap.marvelapi3sia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import br.com.fiap.marvelapi3sia.comics.ComicsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ComicsViewModel by viewModel()

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        viewModel.getComics()

        viewModel.comicsLiveData.observe(this, Observer {

            var comicsString = ""
            it.forEach {
                comicsString += "${it.title}\n"
            }

            textView.text = comicsString
        })

    }
}