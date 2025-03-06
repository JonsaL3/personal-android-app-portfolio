package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.InteresEntity

@Dao
interface InteresDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInteres(interes: InteresEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntereses(intereses: List<InteresEntity>): List<Long>

    @Update
    suspend fun updateInteres(interes: InteresEntity)

    @Delete
    suspend fun deleteInteres(interes: InteresEntity)

    @Query("SELECT * FROM interes WHERE id = :id")
    suspend fun getInteresById(id: Long): InteresEntity?

    @Query("SELECT * FROM interes")
    suspend fun getAllIntereses(): List<InteresEntity>
}