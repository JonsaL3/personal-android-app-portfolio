package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.TrabajoDAO
import com.gonzaloracergalan.portfolio.data.db.entity.TrabajoEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.TrabajoDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class TrabajoRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("TrabajoRepository")
    }

    private val dao: TrabajoDAO by inject()

    suspend fun save(entity: TrabajoEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertTrabajo(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: TrabajoDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertTrabajo(dto.toEntity(id, resumeOwnerId))
    }
}