package br.com.fiap.nac2semestre.characters.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.nac2semestre.characters.entidadesApi.Characters

@Database(entities = [Characters::class],version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}