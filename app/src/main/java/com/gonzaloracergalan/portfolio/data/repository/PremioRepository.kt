package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.PremioDAO
import com.gonzaloracergalan.portfolio.data.db.entity.PremioEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.PremioDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PremioRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("PremioRepository")
    }

    private val dao: PremioDAO by inject()

    suspend fun save(entity: PremioEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertPremio(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: PremioDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertPremio(dto.toEntity(id, resumeOwnerId))
    }
}