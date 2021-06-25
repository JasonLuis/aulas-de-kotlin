package br.com.fiap.nac2semestre.characters.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.fiap.nac2semestre.characters.entidadesApi.Characters

@Dao
interface CharacterDao {

    @Query("SELECT * FROM tb_character")
    suspend fun getCharacters(): List<Characters>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(vararg characters: Characters)

    @Query("DELETE FROM tb_character")
    suspend fun deleteAll()
}