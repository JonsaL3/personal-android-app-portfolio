package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.InteresDAO
import com.gonzaloracergalan.portfolio.data.db.entity.InteresEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.InteresDTO
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class InteresRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("InteresRepository")
    }

    private val dao: InteresDAO by inject()

    suspend fun save(entity: InteresEntity) = runRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertInteres(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: InteresDTO
    ): RepositoryResponse = runRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertInteres(dto.toEntity(id, resumeOwnerId))
    }
}