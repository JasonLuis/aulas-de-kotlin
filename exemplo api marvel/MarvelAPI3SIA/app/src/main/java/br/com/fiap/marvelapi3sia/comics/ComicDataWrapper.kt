package br.com.fiap.marvelapi3sia.comics

data class ComicDataWrapper(
    val code: Int? = null,
    val status: String? = null,
    val data: ComicDataContainer? = null
)

data class ComicDataContainer(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<Comic>? = null
)

data class Comic(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null
)