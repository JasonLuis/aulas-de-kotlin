package br.com.fiap.marvelapi3sia.comics

import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest

val PUBLIC_API_KEY = "9aa9024180a4e122f99029c8e5cf1c63"
val PRIVATE_API_KEY = "0341e966d88759b4443e169ef0ab3b5d629e71f7"

interface MarvelService {

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ) : ComicDataWrapper
}


fun getApiHash(ts: String): String {
    val bytes = MessageDigest
        .getInstance("MD5")
        .digest("${ts}${PRIVATE_API_KEY}${PUBLIC_API_KEY}".toByteArray())

    return bytes.joinToString("") { "%02x".format(it) }
}
