package com.gonzaloracergalan.portfolio

import android.app.Application
import com.gonzaloracergalan.portfolio.data.di.DataModule
import com.gonzaloracergalan.portfolio.domain.di.DomainModule
import com.gonzaloracergalan.portfolio.ui.di.UiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PortfolioApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PortfolioApp)
            modules(
                listOf(
                    DataModule.module,
                    DomainModule.module,
                    UiModule.module
                )
            )
        }
    }
}