package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.db.relation.BasicoWithPerfilesRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface BasicoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasico(basico: BasicoEntity): Long

    @Update
    suspend fun updateBasico(basico: BasicoEntity)

    @Delete
    suspend fun deleteBasico(basico: BasicoEntity)

    @Query("SELECT * FROM basico WHERE id = :id")
    suspend fun getBasicoById(id: Long): BasicoEntity?

    @Query("SELECT * FROM basico")
    suspend fun getAllBasicos(): List<BasicoEntity>

    @Query("""
        SELECT * 
        FROM basico WHERE resumeOwnerId = (
            SELECT resumeId
            FROM resumes
            WHERE isCurrent = 1
            LIMIT 1
        )
    """)
    fun getCurrentBasicoFlow(): Flow<BasicoEntity?>

    @Query("""
        SELECT * 
        FROM basico WHERE resumeOwnerId = (
            SELECT resumeId
            FROM resumes
            WHERE isCurrent = 1
            LIMIT 1
        )
    """)
    fun getCurrentBasicoWithPerfilesFlow(): Flow<BasicoWithPerfilesRelation>
}