package com.gonzaloracergalan.portfolio.data.repository

import com.gonzaloracergalan.portfolio.data.db.dao.PerfilDAO
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.PerfilDTO
import com.gonzaloracergalan.portfolio.data.repository.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class PerfilRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("PerfilRepository")
    }

    private val dao: PerfilDAO by inject()

    val currentPerfilesOfCurrentBasicoFlow = dao.getCurrentAllPerfilesOfCurrentBasicoFlow()

    suspend fun save(entity: PerfilEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        dao.insertPerfil(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: PerfilDTO
    ): RepositoryResponse = runNonTransactionalRoomOperation {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        dao.insertPerfil(dto.toEntity(id, resumeOwnerId))
    }
}