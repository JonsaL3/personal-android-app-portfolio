package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.TrabajoEntity

@Dao
interface TrabajoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrabajo(trabajo: TrabajoEntity): Long

    @Update
    suspend fun updateTrabajo(trabajo: TrabajoEntity)

    @Delete
    suspend fun deleteTrabajo(trabajo: TrabajoEntity)

    @Query("SELECT * FROM trabajo WHERE id = :id")
    suspend fun getTrabajoById(id: Long): TrabajoEntity?

    @Query("SELECT * FROM trabajo")
    suspend fun getAllTrabajos(): List<TrabajoEntity>
}