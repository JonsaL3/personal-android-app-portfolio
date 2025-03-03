package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.ReferenciaEntity

@Dao
interface ReferenciaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReferencia(referencia: ReferenciaEntity): Long

    @Update
    suspend fun updateReferencia(referencia: ReferenciaEntity)

    @Delete
    suspend fun deleteReferencia(referencia: ReferenciaEntity)

    @Query("SELECT * FROM referencia WHERE id = :id")
    suspend fun getReferenciaById(id: Long): ReferenciaEntity?

    @Query("SELECT * FROM referencia")
    suspend fun getAllReferencias(): List<ReferenciaEntity>
}