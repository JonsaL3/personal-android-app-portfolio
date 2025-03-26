package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.CertificacionDAO
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class CertificacionRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("CertificacionRepository")
    }

    private val dao: CertificacionDAO by inject()
}