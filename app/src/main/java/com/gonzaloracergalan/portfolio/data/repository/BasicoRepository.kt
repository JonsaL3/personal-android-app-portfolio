package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.BasicoDAO
import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.BasicoDTO
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class BasicoRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("BasicoRepository")
    }

    private val dao: BasicoDAO by inject()

    suspend fun save(entity: BasicoEntity) = runRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertBasico(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: BasicoDTO
    ): RepositoryResponse = runRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertBasico(dto.toEntity(id, resumeOwnerId))
    }
}