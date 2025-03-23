package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.InteresEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PremioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PremioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPremio(premio: PremioEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPremios(premios: List<PremioEntity>): List<Long>

    @Update
    suspend fun updatePremio(premio: PremioEntity)

    @Delete
    suspend fun deletePremio(premio: PremioEntity)

    @Query("SELECT * FROM premio WHERE id = :id")
    suspend fun getPremioById(id: Long): PremioEntity?

    @Query("SELECT * FROM premio")
    suspend fun getAllPremios(): List<PremioEntity>

    @Query(
        """
        SELECT * 
        FROM premio WHERE resumeOwnerId = (
            SELECT resumeId
            FROM resumes
            WHERE isCurrent = 1
            LIMIT 1
        )
    """
    )
    fun getCurrentAllPremiosFlow(): Flow<List<PremioEntity>>
}