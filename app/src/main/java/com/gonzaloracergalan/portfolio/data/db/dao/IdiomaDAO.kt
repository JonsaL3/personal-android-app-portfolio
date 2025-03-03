package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.IdiomaEntity

@Dao
interface IdiomaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdioma(idioma: IdiomaEntity): Long

    @Update
    suspend fun updateIdioma(idioma: IdiomaEntity)

    @Delete
    suspend fun deleteIdioma(idioma: IdiomaEntity)

    @Query("SELECT * FROM idioma WHERE id = :id")
    suspend fun getIdiomaById(id: Long): IdiomaEntity?

    @Query("SELECT * FROM idioma")
    suspend fun getAllIdiomas(): List<IdiomaEntity>
}