package com.gonzaloracergalan.portfolio.data.repository.util

import com.gonzaloracergalan.portfolio.data.db.dao.PerfilDAO
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.PerfilDTO
import com.gonzaloracergalan.portfolio.data.repository.PortfolioRepository
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PerfilRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("PerfilRepository")
    }

    private val dao: PerfilDAO by inject()

    suspend fun save(entity: PerfilEntity) = runRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertPerfil(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: PerfilDTO
    ): RepositoryResponse = runRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertPerfil(dto.toEntity(id, resumeOwnerId))
    }
}