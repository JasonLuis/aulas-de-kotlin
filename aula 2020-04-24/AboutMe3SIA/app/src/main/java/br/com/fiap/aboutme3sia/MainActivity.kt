package br.com.fiap.aboutme3sia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var buttonProfissao: Button
    lateinit var textViewProfissao: TextView
    lateinit var editTextProfissao: TextView
    lateinit var buttonEditar: Button

    lateinit var textViewNome: TextView
    lateinit var textViewBiografia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonProfissao = findViewById(R.id.buttonProfissao)
        textViewProfissao = findViewById<TextView>(R.id.textViewProfissao)
        editTextProfissao = findViewById<EditText>(R.id.editTextProfissao)
        buttonEditar = findViewById<Button>(R.id.buttonEditar)

        textViewNome = findViewById<TextView>(R.id.textViewNome)
        textViewBiografia = findViewById<TextView>(R.id.textViewBiografia)

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
       
        //chamar outra tela
        buttonEditar.setOnClickListener{
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        buscarDados()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.menu_editar ->{
                val intent = Intent(this, EditActivity::class.java)
                startActivity(intent)
                true
            }else->{
                false
            }
        }

    }

    fun buscarDados() {



        val preferences = getSharedPreferences("aboutme", Context.MODE_PRIVATE)

        textViewNome.text = preferences.getString("CHAVE_NOME","")
        textViewProfissao.text = preferences.getString("CHAVE_PROFISSAO","")
        textViewBiografia.text = preferences.getString("CHAVE_BIOGRAFIA","")
    }

}
