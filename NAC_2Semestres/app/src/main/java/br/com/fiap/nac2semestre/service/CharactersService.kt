package br.com.fiap.nac2semestre.service

import br.com.fiap.nac2semestre.characters.*
import br.com.fiap.nac2semestre.characters.entidadesApi.Response
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest
import java.util.*

interface CharactersService {

    @GET("characters")
    suspend fun getHerois(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
    ): Response

}

fun getHash(ts: String): String {
    val bytes = MessageDigest
        .getInstance("MD5")
        .digest("${ts}${CHAVE_PRIVADA}${CHAVE_PUBLICA}".toByteArray())

    return bytes.joinToString("") { "%02x".format(it) }
}