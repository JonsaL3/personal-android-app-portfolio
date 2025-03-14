package com.gonzaloracergalan.portfolio.data.repository

import androidx.room.withTransaction
import com.gonzaloracergalan.portfolio.data.db.dao.BasicoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PerfilDAO
import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.BasicoDTO
import com.gonzaloracergalan.portfolio.data.repository.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class BasicoRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("BasicoRepository")
    }

    private val basicoDAO: BasicoDAO by inject()
    private val perfilDAO: PerfilDAO by inject()

    val currentBasicoWithPerfilesFlow: Flow<RepositoryResponse> = basicoDAO.getCurrentBasicoWithPerfilesFlow()
        .toRepositoryFlow()

    suspend fun save(entity: BasicoEntity) = runNonTransactionalRoomOperation {
        logger.trace("save: {}", entity)
        basicoDAO.insertBasico(entity)
    }

    suspend fun save(
        id: Long = 0,
        resumeOwnerId: Long,
        dto: BasicoDTO
    ): List<RepositoryResponse> {
        logger.trace("save: id={}, resumeOwnerId={}, DTO={}", id, resumeOwnerId, dto)
        val pairBasicoPerfiles = dto.toEntity(id, resumeOwnerId)
        val basicoEntity = pairBasicoPerfiles.first
        val perfilesEntities = pairBasicoPerfiles.second

        // Nos guardamos las diferentes respuestas de la transacción
        val responses = mutableListOf<RepositoryResponse>()

        // No podemos usar nuestro metodo runNonTransactionalRoomOperation porque necesitamos
        // el id del basico para insertar su lista de perfiles, por lo que lo hacemos
        // aqui a mano
        try {
            portfolioRoomDatabase.withTransaction {
                logger.info("save dto: basico={}", basicoEntity)
                val basicoId = basicoDAO.insertBasico(basicoEntity)
                responses.add(RepositoryResponse.Success(basicoId))
                logger.info("save dto: perfiles={}", perfilesEntities)
                val perfilIds = perfilDAO.insertPerfiles(perfilesEntities.map { it.copy(basicoId = basicoId) })
                responses.add(RepositoryResponse.Success(perfilIds))
            }
        } catch (e: Exception) {
            logger.error("save dto: error={}", e.message)
            responses.clear()
            responses.add(getRepositoryResponseFromException(e))
        }

        logger.trace("save dto: response={}", responses)
        return responses
    }
}