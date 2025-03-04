package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.HabilidadDAO
import com.gonzaloracergalan.portfolio.data.db.entity.HabilidadEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.HabilidadDTO
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class HabilidadRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("HabilidadRepository")
    }

    private val dao: HabilidadDAO by inject()

    suspend fun save(entity: HabilidadEntity) = runRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertHabilidad(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: HabilidadDTO
    ): RepositoryResponse = runRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertHabilidad(dto.toEntity(id, resumeOwnerId))
    }
}