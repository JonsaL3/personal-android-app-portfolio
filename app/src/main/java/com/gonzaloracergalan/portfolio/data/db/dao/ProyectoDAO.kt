package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.ProyectoEntity

@Dao
interface ProyectoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProyecto(proyecto: ProyectoEntity): Long

    @Update
    suspend fun updateProyecto(proyecto: ProyectoEntity)

    @Delete
    suspend fun deleteProyecto(proyecto: ProyectoEntity)

    @Query("SELECT * FROM proyecto WHERE id = :id")
    suspend fun getProyectoById(id: Long): ProyectoEntity?

    @Query("SELECT * FROM proyecto")
    suspend fun getAllProyectos(): List<ProyectoEntity>
}