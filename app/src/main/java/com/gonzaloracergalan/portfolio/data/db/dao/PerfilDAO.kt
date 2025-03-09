package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PerfilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerfil(perfil: PerfilEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerfiles(perfiles: List<PerfilEntity>): List<Long>

    @Update
    suspend fun updatePerfil(perfil: PerfilEntity)

    @Delete
    suspend fun deletePerfil(perfil: PerfilEntity)

    @Query("SELECT * FROM perfil WHERE id = :id")
    suspend fun getPerfilById(id: Long): PerfilEntity?

    @Query("SELECT * FROM perfil")
    suspend fun getAllPerfiles(): List<PerfilEntity>

    @Query(
        """
        SELECT * 
        FROM perfil WHERE basicoId = (
            SELECT basicoId
            FROM resumes
            WHERE resumeId = (
                SELECT resumeId
                FROM resumes
                WHERE isCurrent = 1
                LIMIT 1
            )
        )
    """
    )
    fun getCurrentAllPerfilesOfCurrentBasicoFlow(): Flow<List<PerfilEntity>>
}