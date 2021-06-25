package br.com.fiap.marvelapi3sia.comics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ComicsViewModel(
    private val service: MarvelService
): ViewModel() {

    val comicsLiveData = MutableLiveData<List<Comic>>()

    fun getComics() {

        viewModelScope.launch(Dispatchers.IO) {

            val ts = Date().time.toString()
            val hash = getApiHash(ts)

            val comicDataWrapper = service.getComics(PUBLIC_API_KEY, ts, hash)

            comicsLiveData.postValue(comicDataWrapper.data?.results)
        }
    }
}