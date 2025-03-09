package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.VoluntariadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VoluntariadoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVoluntariado(voluntariado: VoluntariadoEntity): Long

    @Insert
    suspend fun insertVoluntariados(voluntariados: List<VoluntariadoEntity>): List<Long>

    @Update
    suspend fun updateVoluntariado(voluntariado: VoluntariadoEntity)

    @Delete
    suspend fun deleteVoluntariado(voluntariado: VoluntariadoEntity)

    @Query("SELECT * FROM voluntariado WHERE id = :id")
    suspend fun getVoluntariadoById(id: Long): VoluntariadoEntity?

    @Query("SELECT * FROM voluntariado")
    suspend fun getAllVoluntariados(): List<VoluntariadoEntity>

    @Query(
        """
        SELECT * 
        FROM voluntariado WHERE resumeOwnerId = (
            SELECT resumeOwnerId
            FROM resumes
            WHERE isCurrent = 1
            LIMIT 1
        )
    """
    )
    fun getCurrentAllVoluntariadosFlow(): Flow<List<VoluntariadoEntity>>

}