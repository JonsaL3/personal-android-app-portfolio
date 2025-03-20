package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.ReferenciaDAO
import com.gonzaloracergalan.portfolio.data.db.entity.ReferenciaEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.ReferenciaDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class ReferenciaRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("ReferenciaRepository")
    }

    private val dao: ReferenciaDAO by inject()

    suspend fun save(entity: ReferenciaEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertReferencia(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: ReferenciaDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertReferencia(dto.toEntity(id, resumeOwnerId))
    }
}