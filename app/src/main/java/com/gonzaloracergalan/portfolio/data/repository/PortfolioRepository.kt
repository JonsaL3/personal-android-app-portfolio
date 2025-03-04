package com.gonzaloracergalan.portfolio.data.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteReadOnlyDatabaseException
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.KoinComponent
import org.slf4j.LoggerFactory

open class PortfolioRepository : KoinComponent {

    companion object {
        private val logger = LoggerFactory.getLogger("PortfolioRepository")
    }

    suspend fun <T> runRoomOperation(operation: suspend () -> T): RepositoryResponse {
        logger.trace("runRoomOperation")
        // lanzamos la operacion a room para obtener el resultado
        val response = try {
            RepositoryResponse.Success(data = operation())
        } catch (e: Exception) {
            when (e) {
                is SQLiteConstraintException -> RepositoryResponse.Error(
                    error = RepositoryResponse.RepositoryErrorType.ROOM_RESTRICTION
                )

                is SQLiteDiskIOException,
                is SQLiteDatabaseLockedException,
                is SQLiteReadOnlyDatabaseException -> RepositoryResponse.Error(
                    error = RepositoryResponse.RepositoryErrorType.ROOM_IO
                )

                else -> RepositoryResponse.Error(
                    error = RepositoryResponse.RepositoryErrorType.ROOM_GENERIC
                )
            }
        }
        // pintamos las trazas de una forma u otra en función del resultado
        if (response is RepositoryResponse.Error) {
            logger.warn("runRoomOperation: ${response.error}")
        } else {
            logger.info("runRoomOperation response: {}", response)
        }
        // retornamos el resultado
        return response
    }
}