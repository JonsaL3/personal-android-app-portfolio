package com.gonzaloracergalan.portfolio.data.util

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteReadOnlyDatabaseException
import androidx.room.withTransaction
import com.gonzaloracergalan.portfolio.common.response.RepositoryResponse
import com.gonzaloracergalan.portfolio.data.db.PortfolioRoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

abstract class PortfolioRepository : KoinComponent {

    protected val portfolioRoomDatabase: PortfolioRoomDatabase by inject()

    companion object {
        private val logger = LoggerFactory.getLogger("PortfolioRepository")
    }

    /**
     * Mapeamos un flow cualquiera a una respuesta del repositorio con los errores tratados.
     */
    protected fun <T> Flow<T>.toRepositoryFlow() = this
        .onStart { RepositoryResponse.Loading }
        .map { RepositoryResponse.Success(it) as RepositoryResponse }
        .catch { emit(getRepositoryResponseFromException(it)) }

    /**
     * Metodo que ejecuta una operación de room y trata sus correspondientes excepciones.
     * Es Non Transactional porque en caso de querer ejecutar una transacción con varios DAOs
     * necesito que se propague la excepción y se haga rollback.
     */
    protected suspend fun <T> runNonTransactionalRoomOperation(
        operation: suspend () -> T
    ): RepositoryResponse {
        logger.trace("runNonTransactionalRoomOperation")
        // lanzamos la operacion a room para obtener el resultado
        val response = try {
            RepositoryResponse.Success(data = operation())
        } catch (e: Exception) {
            logger.warn("runNonTransactionalRoomOperation: {}", e.message)
            getRepositoryResponseFromException(e)
        }
        // pintamos las trazas de una forma u otra en función del resultado
        if (response is RepositoryResponse.Error) {
            logger.warn("runNonTransactionalRoomOperation: ${response.error}")
        } else {
            logger.info("runNonTransactionalRoomOperation response: {}", response)
        }
        // retornamos el resultado
        return response
    }

    /**
     * Metodo que ejecuta una operación transaccional de room y trata sus correspondientes excepciones.
     *
     * @param operations Lista de operaciones a ejecutar en la transacción.
     * @return Lista de respuestas de las operaciones. Devuelve un único error si alguna de
     * las operaciones falla (debido a que se trata de una transaccion, o todas o ninguna).
     */
    protected suspend fun <T> runTransactionalRoomOperation(
        operations: List<suspend () -> T>
    ): List<RepositoryResponse> {
        logger.trace("runTransactionalRoomOperation")
        // lanzamos las operaciones a room para obtener los resultados
        val responses: MutableList<RepositoryResponse> = mutableListOf()

        try {
            portfolioRoomDatabase.withTransaction {
                // Lanzamos todas las operaciones y guardamos sus resultados a no ser que una
                // de ellas falle.
                val operationResponses = operations.mapIndexed { index: Int, op: suspend () -> T ->
                    logger.debug("runTransactionalRoomOperation: ejecutando operación $index")
                    RepositoryResponse.Success(op())
                }
                // Si tod.o fue bien nos guardamos las respuestas de success de todas ellas...
                logger.trace("runTransactionalRoomOperation: operaciones ejecutadas")
                responses.addAll(operationResponses)
            }
        } catch (e: Exception) {
            // Si se produce un solo error en la transaccion devolvemos un unico error indicando
            // el error que ha provocado que la transaccion se muera
            logger.warn("runTransactionalRoomOperation: {}", e.message)
            val error = getRepositoryResponseFromException(e)
            // Nos guardamos una única response con el error que ha tirado la transacción.
            responses.clear()
            responses.add(error)
        }

        return responses
    }

    /**
     * Metodo que convierte una excepción de room en una respuesta de error.
     */
    protected fun getRepositoryResponseFromException(e: Throwable): RepositoryResponse {
        logger.warn("getRepositoryResponseFromException throwable: {}", e.message)
        return when (e) {
            is SQLiteConstraintException -> RepositoryResponse.Error(
                error = RepositoryResponse.RepositoryErrorType.ROOM_RESTRICTION
            )

            is SQLiteDiskIOException,
            is SQLiteDatabaseLockedException,
            is SQLiteReadOnlyDatabaseException -> RepositoryResponse.Error(
                error = RepositoryResponse.RepositoryErrorType.ROOM_IO
            )

            else -> RepositoryResponse.Error(
                error = RepositoryResponse.RepositoryErrorType.UNKNOWN_ERROR
            )
        }
    }
}