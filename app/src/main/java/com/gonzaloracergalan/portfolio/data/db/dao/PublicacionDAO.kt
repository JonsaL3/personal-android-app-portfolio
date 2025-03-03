package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.PublicacionEntity

@Dao
interface PublicacionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublicacion(publicacion: PublicacionEntity): Long

    @Update
    suspend fun updatePublicacion(publicacion: PublicacionEntity)

    @Delete
    suspend fun deletePublicacion(publicacion: PublicacionEntity)

    @Query("SELECT * FROM publicacion WHERE id = :id")
    suspend fun getPublicacionById(id: Long): PublicacionEntity?

    @Query("SELECT * FROM publicacion")
    suspend fun getAllPublicaciones(): List<PublicacionEntity>
}