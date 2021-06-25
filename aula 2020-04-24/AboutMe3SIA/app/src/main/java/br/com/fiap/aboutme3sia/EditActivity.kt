package br.com.fiap.aboutme3sia

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //Utilizado para recuperar valoress
        val preferences = getSharedPreferences("aboutme", Context.MODE_PRIVATE)

        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val editTextProfissao = findViewById<EditText>(R.id.editTextProfissao)
        val editTextBiografia = findViewById<EditText>(R.id.editTextBiografia)
        val btnSalvar = findViewById<Button>(R.id.buttonSalvar)

        editTextNome.setText(preferences.getString("CHAVE_NOME",""))
        editTextProfissao.setText(preferences.getString("CHAVE_PROFISSAO",""))
        editTextBiografia.setText(preferences.getString("CHAVE_BIOGRAFIA",""))

        btnSalvar.setOnClickListener {
            val nome = editTextNome.text.toString()
            val profissao = editTextProfissao.text.toString()
            val biografia = editTextBiografia.text.toString()

            val edit = preferences.edit()
            edit.putString("CHAVE_NOME",nome)
            edit.putString("CHAVE_PROFISSAO",profissao)
            edit.putString("CHAVE_BIOGRAFIA",biografia)
            edit.apply()

            Toast.makeText(this,"Salvo com sucesso",Toast.LENGTH_SHORT).show()
        }

    }
}