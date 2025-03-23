package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.entity.CertificadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CertificacionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCertificado(certificado: CertificadoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCertificados(perfiles: List<CertificadoEntity>): List<Long>

    @Update
    suspend fun updateCertificado(certificado: CertificadoEntity)

    @Delete
    suspend fun deleteCertificado(certificado: CertificadoEntity)

    @Query("SELECT * FROM certificado WHERE id = :id")
    suspend fun getCertificadoById(id: Long): CertificadoEntity?

    @Query("SELECT * FROM certificado")
    suspend fun getAllCertificados(): List<CertificadoEntity>

    @Query(
        """
        SELECT * 
        FROM certificado WHERE resumeOwnerId = (
            SELECT resumeId
            FROM resumes
            WHERE isCurrent = 1
            LIMIT 1
        )
    """
    )
    fun getCurrentAllCertificadosFlow(): Flow<List<CertificadoEntity>>
}