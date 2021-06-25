package br.com.fiap.marvelapi3sia

import android.app.Application
import br.com.fiap.marvelapi3sia.comics.ComicsViewModel
import br.com.fiap.marvelapi3sia.comics.MarvelService
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicsApplication: Application() {

    val comicsModule = module {
        single { provideRetrofit() }
        single { provideMarvelService(get()) }
        viewModel { ComicsViewModel(get()) }
    }

    private fun provideMarvelService(retrofit: Retrofit) : MarvelService {
        return retrofit.create(MarvelService::class.java)
    }

    private fun provideRetrofit() : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComicsApplication)
            modules(comicsModule)
        }
    }
}