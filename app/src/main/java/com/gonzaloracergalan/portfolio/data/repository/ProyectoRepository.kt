package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.ProyectoDAO
import com.gonzaloracergalan.portfolio.data.db.entity.ProyectoEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.ProyectoDTO
import com.gonzaloracergalan.portfolio.data.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class ProyectoRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("ProyectoRepository")
    }

    private val dao: ProyectoDAO by inject()

    suspend fun save(entity: ProyectoEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertProyecto(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: ProyectoDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertProyecto(dto.toEntity(id, resumeOwnerId))
    }
}