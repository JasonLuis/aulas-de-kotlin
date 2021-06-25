package br.com.fiap.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val numberLivedata = MutableLiveData<Int>()
    fun getLiveData(): LiveData<Int> = numberLivedata

    fun generateNumber() {
        val number = (1..100).random()
        numberLivedata.value = number
    }


}