package br.com.fiap.aboutme3sia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonProfissao: Button = findViewById(R.id.buttonProfissao)
        val textViewProfissao = findViewById<TextView>(R.id.textViewProfissao)
        val editTextProfissao = findViewById<EditText>(R.id.editTextProfissao)

        buttonProfissao.setOnClickListener{
            val profissao = editTextProfissao.text.toString()
            if(profissao.isNotEmpty()) {
                textViewProfissao.text = profissao
                buttonProfissao.visibility = View.GONE
                editTextProfissao.visibility = View.GONE
            }else{
                editTextProfissao.error = "Digite sua profissão"
            }
        }

        textViewProfissao.setOnClickListener{
            buttonProfissao.visibility = View.VISIBLE
            editTextProfissao.visibility = View.VISIBLE
        }

    }
}
