package br.com.fiap.nac2semestre


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val textViewName: TextView = findViewById(R.id.textName)
        val textViewSeries: TextView = findViewById(R.id.textSeries)

        textViewName.setText(getIntent().getStringExtra("NAME"))
        textViewSeries.setText(getIntent().getStringExtra("STORIES").toString())
        val imgFile = getIntent().getStringExtra("IMAGE").toString()
       Picasso.get().load(imgFile).into(imgDetalhes)
    }
}