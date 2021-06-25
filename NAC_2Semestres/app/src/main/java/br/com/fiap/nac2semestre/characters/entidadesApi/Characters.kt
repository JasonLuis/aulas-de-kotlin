package br.com.fiap.nac2semestre.characters.entidadesApi


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
@Entity(tableName = "tb_character")
data class Characters(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val description: String,
    @Ignore var thumbnail: Thumbnail? = null,
    @Ignore var modified: String? = null,
    @Ignore var series:Series? = null,
){

    constructor(
        id:Int,
        name: String,
        description: String
    ) : this(
        id=id,
        name=name,
        description=description,
        null,
        null,
        null
    )
}