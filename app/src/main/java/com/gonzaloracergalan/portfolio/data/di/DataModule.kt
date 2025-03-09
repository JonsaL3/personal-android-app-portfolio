package com.gonzaloracergalan.portfolio.data.di

import androidx.room.Room
import com.gonzaloracergalan.portfolio.data.datastore.CurrentJsonResumeDatastoreManager
import com.gonzaloracergalan.portfolio.data.db.PortfolioRoomDatabase
import com.gonzaloracergalan.portfolio.data.repository.BasicoRepository
import com.gonzaloracergalan.portfolio.data.repository.CertificacionRepository
import com.gonzaloracergalan.portfolio.data.repository.EducacionRepository
import com.gonzaloracergalan.portfolio.data.repository.HabilidadRepository
import com.gonzaloracergalan.portfolio.data.repository.IdiomaRepository
import com.gonzaloracergalan.portfolio.data.repository.InteresRepository
import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import com.gonzaloracergalan.portfolio.data.repository.PerfilRepository
import com.gonzaloracergalan.portfolio.data.repository.PremioRepository
import com.gonzaloracergalan.portfolio.data.repository.ProyectoRepository
import com.gonzaloracergalan.portfolio.data.repository.PublicacionRepository
import com.gonzaloracergalan.portfolio.data.repository.ReferenciaRepository
import com.gonzaloracergalan.portfolio.data.repository.TrabajoRepository
import com.gonzaloracergalan.portfolio.data.repository.VoluntariadoRepository
import com.gonzaloracergalan.portfolio.util.PortfolioKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule : PortfolioKoinModule {
    override val module: Module
        get() = module {
            // room database
            single {
                Room.databaseBuilder(
                    androidContext(),
                    PortfolioRoomDatabase::class.java, "portfolio-database"
                ).build()
            }
            // datastores
            single { CurrentJsonResumeDatastoreManager() }
            // room daos
            single { get<PortfolioRoomDatabase>().basicoDao() }
            single { get<PortfolioRoomDatabase>().certificacionDao() }
            single { get<PortfolioRoomDatabase>().educacionDao() }
            single { get<PortfolioRoomDatabase>().habilidadDao() }
            single { get<PortfolioRoomDatabase>().idiomaDao() }
            single { get<PortfolioRoomDatabase>().interesDao() }
            single { get<PortfolioRoomDatabase>().jsonResumeWrapperDao() }
            single { get<PortfolioRoomDatabase>().perfilDao() }
            single { get<PortfolioRoomDatabase>().premioDao() }
            single { get<PortfolioRoomDatabase>().proyectoDao() }
            single { get<PortfolioRoomDatabase>().publicacionDao() }
            single { get<PortfolioRoomDatabase>().referenciaDao() }
            single { get<PortfolioRoomDatabase>().trabajoDao() }
            single { get<PortfolioRoomDatabase>().voluntariadoDao() }
            // repositories
            single { JsonResumeWrapperRepository() }
            single { CurrentJsonResumeRepository() }
            single { BasicoRepository() }
            single { CertificacionRepository() }
            single { EducacionRepository() }
            single { HabilidadRepository() }
            single { IdiomaRepository() }
            single { InteresRepository() }
            single { PerfilRepository() }
            single { PremioRepository() }
            single { ProyectoRepository() }
            single { PublicacionRepository() }
            single { ReferenciaRepository() }
            single { TrabajoRepository() }
            single { VoluntariadoRepository() }
        }
}