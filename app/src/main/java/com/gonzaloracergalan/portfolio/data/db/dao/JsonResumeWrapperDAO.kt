package com.gonzaloracergalan.portfolio.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
}