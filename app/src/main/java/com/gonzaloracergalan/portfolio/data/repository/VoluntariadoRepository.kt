package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.VoluntariadoDAO
import com.gonzaloracergalan.portfolio.data.db.entity.VoluntariadoEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.VoluntariadoDTO
import com.gonzaloracergalan.portfolio.data.repository.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class VoluntariadoRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("VoluntariadoRepository")
    }

    private val dao: VoluntariadoDAO by inject()

    suspend fun save(entity: VoluntariadoEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertVoluntariado(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: VoluntariadoDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertVoluntariado(dto.toEntity(id, resumeOwnerId))
    }
}