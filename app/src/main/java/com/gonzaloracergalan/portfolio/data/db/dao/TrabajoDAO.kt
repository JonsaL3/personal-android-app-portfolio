package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.TrabajoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrabajoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrabajo(trabajo: TrabajoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrabajos(trabajos: List<TrabajoEntity>): List<Long>

    @Update
    suspend fun updateTrabajo(trabajo: TrabajoEntity)

    @Delete
    suspend fun deleteTrabajo(trabajo: TrabajoEntity)

    @Query("SELECT * FROM trabajo WHERE id = :id")
    suspend fun getTrabajoById(id: Long): TrabajoEntity?

    @Query("SELECT * FROM trabajo")
    suspend fun getAllTrabajos(): List<TrabajoEntity>

    @Query(
        """
        SELECT * 
        FROM trabajo WHERE resumeOwnerId = (
            SELECT resumeId
            FROM resumes
            WHERE isCurrent = 1
            LIMIT 1
        )
    """
    )
    fun getCurrentAllTrabajosFlow(): Flow<List<TrabajoEntity>>
}