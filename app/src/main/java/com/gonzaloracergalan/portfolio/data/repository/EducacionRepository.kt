package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.EducacionDAO
import com.gonzaloracergalan.portfolio.data.db.entity.EducacionEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.EducacionDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class EducacionRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("EducacionRepository")
    }

    private val dao: EducacionDAO by inject()

    suspend fun save(entity: EducacionEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertEducacion(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: EducacionDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertEducacion(dto.toEntity(id, resumeOwnerId))
    }
}