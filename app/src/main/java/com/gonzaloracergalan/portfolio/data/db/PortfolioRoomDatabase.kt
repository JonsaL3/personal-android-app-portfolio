package com.gonzaloracergalan.portfolio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gonzaloracergalan.portfolio.data.db.converter.RoomConverter
import com.gonzaloracergalan.portfolio.data.db.dao.BasicoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.CertificacionDAO
import com.gonzaloracergalan.portfolio.data.db.dao.EducacionDAO
import com.gonzaloracergalan.portfolio.data.db.dao.HabilidadDAO
import com.gonzaloracergalan.portfolio.data.db.dao.IdiomaDAO
import com.gonzaloracergalan.portfolio.data.db.dao.InteresDAO
import com.gonzaloracergalan.portfolio.data.db.dao.JsonResumeWrapperDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PerfilDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PremioDAO
import com.gonzaloracergalan.portfolio.data.db.dao.ProyectoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PublicacionDAO
import com.gonzaloracergalan.portfolio.data.db.dao.ReferenciaDAO
import com.gonzaloracergalan.portfolio.data.db.dao.TrabajoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.VoluntariadoDAO
import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.CertificadoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.EducacionEntity
import com.gonzaloracergalan.portfolio.data.db.entity.HabilidadEntity
import com.gonzaloracergalan.portfolio.data.db.entity.IdiomaEntity
import com.gonzaloracergalan.portfolio.data.db.entity.InteresEntity
import com.gonzaloracergalan.portfolio.data.db.entity.JsonResumeWrapperEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PremioEntity
import com.gonzaloracergalan.portfolio.data.db.entity.ProyectoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PublicacionEntity
import com.gonzaloracergalan.portfolio.data.db.entity.ReferenciaEntity
import com.gonzaloracergalan.portfolio.data.db.entity.TrabajoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.VoluntariadoEntity

@Database(
    entities = [
        JsonResumeWrapperEntity::class,
        BasicoEntity::class,
        PerfilEntity::class,
        CertificadoEntity::class,
        EducacionEntity::class,
        HabilidadEntity::class,
        IdiomaEntity::class,
        InteresEntity::class,
        PremioEntity::class,
        ProyectoEntity::class,
        PublicacionEntity::class,
        ReferenciaEntity::class,
        TrabajoEntity::class,
        VoluntariadoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class PortfolioRoomDatabase : RoomDatabase() {
    abstract fun basicoDao(): BasicoDAO
    abstract fun certificacionDao(): CertificacionDAO
    abstract fun educacionDao(): EducacionDAO
    abstract fun habilidadDao(): HabilidadDAO
    abstract fun idiomaDao(): IdiomaDAO
    abstract fun interesDao(): InteresDAO
    abstract fun jsonResumeWrapperDao(): JsonResumeWrapperDAO
    abstract fun perfilDao(): PerfilDAO
    abstract fun premioDao(): PremioDAO
    abstract fun proyectoDao(): ProyectoDAO
    abstract fun publicacionDao(): PublicacionDAO
    abstract fun referenciaDao(): ReferenciaDAO
    abstract fun trabajoDao(): TrabajoDAO
    abstract fun voluntariadoDao(): VoluntariadoDAO
}