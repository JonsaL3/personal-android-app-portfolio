package com.gonzaloracergalan.portfolio.data.di

import androidx.room.Room
import com.gonzaloracergalan.portfolio.data.db.PortfolioRoomDatabase
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
        }
}