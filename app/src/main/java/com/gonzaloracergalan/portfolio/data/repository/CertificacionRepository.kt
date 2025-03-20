package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.CertificacionDAO
import com.gonzaloracergalan.portfolio.data.db.entity.CertificadoEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.CertificadoDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class CertificacionRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("CertificacionRepository")
    }

    private val dao: CertificacionDAO by inject()

    suspend fun save(entity: CertificadoEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertCertificado(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: CertificadoDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertCertificado(dto.toEntity(id, resumeOwnerId))
    }
}