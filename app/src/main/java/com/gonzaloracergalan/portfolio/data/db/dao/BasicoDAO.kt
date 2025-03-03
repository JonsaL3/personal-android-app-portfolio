package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity

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
}