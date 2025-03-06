package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.HabilidadEntity

@Dao
interface HabilidadDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabilidad(habilidad: HabilidadEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabilidades(habilidades: List<HabilidadEntity>): List<Long>

    @Update
    suspend fun updateHabilidad(habilidad: HabilidadEntity)

    @Delete
    suspend fun deleteHabilidad(habilidad: HabilidadEntity)

    @Query("SELECT * FROM habilidad WHERE id = :id")
    suspend fun getHabilidadById(id: Long): HabilidadEntity?

    @Query("SELECT * FROM habilidad")
    suspend fun getAllHabilidades(): List<HabilidadEntity>
}