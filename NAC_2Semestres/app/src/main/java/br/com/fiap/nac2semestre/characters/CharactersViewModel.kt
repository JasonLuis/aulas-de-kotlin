package br.com.fiap.nac2semestre.characters

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.nac2semestre.characters.database.CharacterDao

import br.com.fiap.nac2semestre.characters.entidadesApi.Characters
import br.com.fiap.nac2semestre.characters.entidadesApi.Response
import br.com.fiap.nac2semestre.service.CharactersService
import br.com.fiap.nac2semestre.service.getHash
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import java.util.*

class CharactersViewModel(
    private val service: CharactersService,
    private val characterDao: CharacterDao
): ViewModel() {
    val heroiLiveData = MutableLiveData<List<Characters>>()

    val error = MutableLiveData<Boolean>()
    init {
        fetchHerois()
    }

     private fun fetchHerois(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Banco Local
                val cacheData = characterDao.getCharacters()
                heroiLiveData.postValue(cacheData)

                //Servidor
                val ts: String = Date().time.toString()
                val hash: String = getHash(ts)

                val result: Response = service.getHerois(CHAVE_PUBLICA, ts, hash)
                heroiLiveData.postValue(result.data?.results)

                //Atualizar o Banco
                characterDao.deleteAll()
                characterDao.insertCharacter(*result.data?.results!!.toTypedArray())
            } catch (t: Throwable){
                error.postValue(true)
            }
        }
    }


}


