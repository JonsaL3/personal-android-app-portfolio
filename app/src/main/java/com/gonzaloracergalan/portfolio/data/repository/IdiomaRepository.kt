package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.IdiomaDAO
import com.gonzaloracergalan.portfolio.data.db.entity.IdiomaEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.IdiomaDTO
import com.gonzaloracergalan.portfolio.data.repository.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class IdiomaRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("IdiomaRepository")
    }

    private val dao: IdiomaDAO by inject()

    suspend fun save(entity: IdiomaEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertIdioma(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: IdiomaDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertIdioma(dto.toEntity(id, resumeOwnerId))
    }
}