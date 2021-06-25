package br.com.fiap.nac2semestre

import android.app.Application
import android.content.Context
import androidx.room.Room
import br.com.fiap.nac2semestre.characters.CharactersViewModel
import br.com.fiap.nac2semestre.characters.database.CharacterDao
import br.com.fiap.nac2semestre.characters.database.CharacterDatabase


import br.com.fiap.nac2semestre.service.CharactersService
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterApplication: Application()  {

    val characterModule = module {
        //Service
        single { provideRetrofit() }
        single { provideCharacterService(get()) }

        //Database
        single { provideCharacterDatabase(get()) }
        single { provideCharacterDao(get()) }

        viewModel { CharactersViewModel(get(),get()) }
    }

    private fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }

    private fun provideCharacterDatabase(context: Context):CharacterDatabase{
        return Room
            .databaseBuilder(context, CharacterDatabase::class.java,"characterdatabase")
            .build()
    }

    private fun provideCharacterService(retrofit: Retrofit):CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

    private fun provideRetrofit():Retrofit{
         val retrofit =Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //http://gateway.marvel.com/v1/public/characters?ts=1599834283&apikey=1611ea3bc24cff75db4b4ca0afb199c6&hash=47b873e6c4ef81632f94536e68797348
        return retrofit
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@CharacterApplication)
            modules(characterModule)
        }
    }

}