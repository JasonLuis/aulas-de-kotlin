package br.com.fiap.quiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentQuiz: Fragment(R.layout.fragment_quiz) {

    lateinit var buttonProxima: Button
    lateinit var radioGroupOpcoes: RadioGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonProxima = view.findViewById(R.id.buttonProxima)
        radioGroupOpcoes = view.findViewById(R.id.radioGroupOpcoes)

        buttonProxima.setOnClickListener{

            val opcaoSelecionada = radioGroupOpcoes.checkedRadioButtonId

            when(opcaoSelecionada){
                R.id.opcao1 -> {
                    navegarRespostaErrada()
                }
                R.id.opcao2 -> {
                    navegarRespostaErrada()
                }
                R.id.opcao3 -> {
                    navegarRespostaErrada()
                }
                R.id.opcao4 -> {
                    navegarRespostaCorreta()
                }
                else -> {
                    Toast.makeText(context, "Selecione uma opção", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navegarRespostaCorreta(){
        findNavController().navigate(R.id.action_fragmentQuiz_to_fragmentRespostaCorreta)
    }

    private fun navegarRespostaErrada(){
        findNavController().navigate(R.id.action_fragmentQuiz_to_fragmentRespostaErrada)
    }
}