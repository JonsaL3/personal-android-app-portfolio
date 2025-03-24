package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gonzaloracergalan.portfolio.data.db.calculated.ActiveResumeSectionsCalculated
import com.gonzaloracergalan.portfolio.data.db.entity.JsonResumeWrapperEntity

@Dao
interface JsonResumeWrapperDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResume(resume: JsonResumeWrapperEntity): Long

    @Update
    suspend fun updateResume(resume: JsonResumeWrapperEntity)

    @Delete
    suspend fun deleteResume(resume: JsonResumeWrapperEntity)

    @Query("SELECT * FROM resumes WHERE resumeId = :id")
    suspend fun getResumeById(id: Long): JsonResumeWrapperEntity?

    @Query("SELECT * FROM resumes")
    suspend fun getAllResumes(): List<JsonResumeWrapperEntity>

    @Query("SELECT count(*) > 0 FROM resumes WHERE isCurrent = 1")
    suspend fun isCurrentSetted(): Boolean

    @Query("UPDATE resumes SET isCurrent = 0")
    suspend fun setAllCurrentToFalse(): Int

    @Query("UPDATE resumes SET isCurrent = 1 WHERE resumeId = :id")
    suspend fun setCurrentResume(id: Long): Int

    @Query(
        """
        SELECT 
            r.resumeId,
            TRIM(
                CASE WHEN COUNT(DISTINCT b.id) > 0 THEN 'BASICO,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT c.id) > 0 THEN 'CERTIFICADOS,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT e.id) > 0 THEN 'EDUCACION,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT h.id) > 0 THEN 'HABILIDAD,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT i.id) > 0 THEN 'IDIOMA,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT inte.id) > 0 THEN 'INTERES,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT p.id) > 0 THEN 'PROYECTO,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT pu.id) > 0 THEN 'PUBLICACION,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT re.id) > 0 THEN 'REFERENCIA,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT t.id) > 0 THEN 'TRABAJO,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT v.id) > 0 THEN 'VOLUNTARIADO,' ELSE '' END ||
                CASE WHEN COUNT(DISTINCT pe.id) > 0 THEN 'PERFIL,' ELSE '' END
            , ',') AS activeResumeSections
        FROM resumes r
            LEFT JOIN basico b        ON b.id = r.resumeId
            LEFT JOIN certificado c   ON c.resumeOwnerId = r.resumeId
            LEFT JOIN educacion e     ON e.resumeOwnerId = r.resumeId
            LEFT JOIN habilidad h     ON h.resumeOwnerId = r.resumeId
            LEFT JOIN idioma i        ON i.resumeOwnerId = r.resumeId
            LEFT JOIN interes inte    ON inte.resumeOwnerId = r.resumeId
            LEFT JOIN proyecto p      ON p.resumeOwnerId = r.resumeId
            LEFT JOIN publicacion pu  ON pu.resumeOwnerId = r.resumeId
            LEFT JOIN referencia re   ON re.resumeOwnerId = r.resumeId
            LEFT JOIN trabajo t       ON t.resumeOwnerId = r.resumeId
            LEFT JOIN voluntariado v  ON v.resumeOwnerId = r.resumeId
            LEFT JOIN perfil pe       ON pe.basicoId = b.id
        WHERE r.isCurrent = 1
        LIMIT 1
    """
    ) gonzalo estabas obteniendo esta lista que necesitas para el menu principal,
    esta lista luego cada una tiene que ir con su onclick y navegación en el mapOf de la interfaz
    fun getCurrentActiveResumeSections(): ActiveResumeSectionsCalculated
}