package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.EducacionEntity

@Dao
interface EducacionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEducacion(educacion: EducacionEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEducaciones(educacion: List<EducacionEntity>): List<Long>

    @Update
    suspend fun updateEducacion(educacion: EducacionEntity)

    @Delete
    suspend fun deleteEducacion(educacion: EducacionEntity)

    @Query("SELECT * FROM educacion WHERE id = :id")
    suspend fun getEducacionById(id: Long): EducacionEntity?

    @Query("SELECT * FROM educacion")
    suspend fun getAllEducacion(): List<EducacionEntity>
}