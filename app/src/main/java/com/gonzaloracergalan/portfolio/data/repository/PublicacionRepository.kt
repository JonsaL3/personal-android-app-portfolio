package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.PublicacionDAO
import com.gonzaloracergalan.portfolio.data.db.entity.PublicacionEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.PublicacionDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PublicacionRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("PublicacionRepository")
    }

    private val dao: PublicacionDAO by inject()

    suspend fun save(entity: PublicacionEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertPublicacion(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: PublicacionDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertPublicacion(dto.toEntity(id, resumeOwnerId))
    }
}