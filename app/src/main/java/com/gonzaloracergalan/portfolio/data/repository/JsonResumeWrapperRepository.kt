package com.gonzaloracergalan.portfolio.data.repository

import androidx.room.withTransaction
import com.gonzaloracergalan.portfolio.data.db.dao.BasicoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.CertificacionDAO
import com.gonzaloracergalan.portfolio.data.db.dao.EducacionDAO
import com.gonzaloracergalan.portfolio.data.db.dao.HabilidadDAO
import com.gonzaloracergalan.portfolio.data.db.dao.IdiomaDAO
import com.gonzaloracergalan.portfolio.data.db.dao.InteresDAO
import com.gonzaloracergalan.portfolio.data.db.dao.JsonResumeWrapperDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PerfilDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PremioDAO
import com.gonzaloracergalan.portfolio.data.db.dao.ProyectoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.PublicacionDAO
import com.gonzaloracergalan.portfolio.data.db.dao.ReferenciaDAO
import com.gonzaloracergalan.portfolio.data.db.dao.TrabajoDAO
import com.gonzaloracergalan.portfolio.data.db.dao.VoluntariadoDAO
import com.gonzaloracergalan.portfolio.data.db.entity.JsonResumeWrapperEntity
import com.gonzaloracergalan.portfolio.data.dt.dto.JsonResumeWrapperDTO
import com.gonzaloracergalan.portfolio.data.repository.util.PortfolioRepository
import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class JsonResumeWrapperRepository : PortfolioRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger("JsonResumeWrapperRepository")
    }

    // Trabajar con esta entidad lleva varios daos de la mano
    private val jsonResumeWrapperDAO: JsonResumeWrapperDAO by inject()
    private val basicoDAO: BasicoDAO by inject()
    private val certificacionDAO: CertificacionDAO by inject()
    private val educacionDAO: EducacionDAO by inject()
    private val habilidadDAO: HabilidadDAO by inject()
    private val idiomaDAO: IdiomaDAO by inject()
    private val interesDAO: InteresDAO by inject()
    private val perfilDAO: PerfilDAO by inject()
    private val premioDAO: PremioDAO by inject()
    private val proyectoDAO: ProyectoDAO by inject()
    private val publicacionDAO: PublicacionDAO by inject()
    private val referenciaDAO: ReferenciaDAO by inject()
    private val trabajoDAO: TrabajoDAO by inject()
    private val voluntariadoDAO: VoluntariadoDAO by inject()

    suspend fun setCurrentResumeId(resumeId: Long): List<RepositoryResponse> {
        logger.trace("setCurrentResumeId: resumeId={}", resumeId)
        val operations = mutableListOf<suspend () -> Int>()
        // necesito que se seteen correctamente todas a false si o si antes de setear como actual
        // un nuevo resume
        operations.add {
            logger.info("setCurrentResumeId: Setting all current resumes to false")
            jsonResumeWrapperDAO.setAllCurrentToFalse()
        }
        operations.add {
            logger.info("setCurrentResumeId: Setting current resume ($resumeId) to true")
            jsonResumeWrapperDAO.setCurrentResume(resumeId)
        }
        val response = runTransactionalRoomOperation(operations)
        logger.info("setCurrentResumeId: response={}", response)
        return response
    }

    suspend fun save(entity: JsonResumeWrapperEntity): RepositoryResponse =
        runNonTransactionalRoomOperation {
            logger.trace("save: {}", entity)
            if (jsonResumeWrapperDAO.isCurrentSetted()) {
                logger.warn("save: There is already a current resume id setted to true, cannot save another one.")
                RepositoryResponse.Error(RepositoryResponse.RepositoryErrorType.ROOM_RESTRICTION)
            } else {
                logger.info("save: Inserting new resume")
                jsonResumeWrapperDAO.insertResume(entity)
            }
        }

    suspend fun save(
        resumeId: Long = 0,
        dto: JsonResumeWrapperDTO
    ): List<RepositoryResponse> {
        logger.trace("save: resumeId={}, DTO={}", resumeId, dto)
        // Primero convertimos a entity todos los elementos del dto
        val pairBasicoPerfiles = dto.basico?.toEntity(0, resumeId)
        val basicoEntity = pairBasicoPerfiles?.first
        val perfilesEntities = pairBasicoPerfiles?.second
        val certificacionEntities = dto.certificados?.map { it.toEntity(0, resumeId) }
        val educacionEntities = dto.educacion?.map { it.toEntity(0, resumeId) }
        val habilidadEntities = dto.habilidades?.map { it.toEntity(0, resumeId) }
        val idiomaEntities = dto.idiomas?.map { it.toEntity(0, resumeId) }
        val interesEntities = dto.intereses?.map { it.toEntity(0, resumeId) }
        val premiosEntities = dto.premios?.map { it.toEntity(0, resumeId) }
        val proyectoEntities = dto.proyectos?.map { it.toEntity(0, resumeId) }
        val publicacionEntities = dto.publicaciones?.map { it.toEntity(0, resumeId) }
        val referenciaEntities = dto.referencias?.map { it.toEntity(0, resumeId) }
        val trabajoEntities = dto.trabajo?.map { it.toEntity(0, resumeId) }
        val voluntariadoEntities = dto.voluntariado?.map { it.toEntity(0, resumeId) }
        // ahora convertimos a entity el jsonResumeWrapperDTO
        val jsonResumeWrapperEntity = dto.toEntity(resumeId)

        // Nos guardamos las diferentes respuestas de la transacción
        val responses = mutableListOf<RepositoryResponse>()

        // procedemos con la inserccion de los elementos, en este caso no podremos usar
        // runTransactionalRoomOperation, deberemos hacerlo a mano, porque necesitamos guardarnos
        // el id del jsonResumeWrapperEntity para poder relacionar los elementos con él.
        try {
            portfolioRoomDatabase.withTransaction {
                // insertamos el id del jsonresume
                val jsonResumeId = jsonResumeWrapperDAO.insertResume(jsonResumeWrapperEntity)

                // insertamos lo basico con su lista de perfiles
                basicoEntity?.let { basico ->
                    logger.info("save: BasicoEntity={}", basico)
                    val mBasicoEntity = basico.copy(resumeOwnerId = jsonResumeId)
                    val basicoId = basicoDAO.insertBasico(mBasicoEntity)
                    responses.add(RepositoryResponse.Success(basicoId))
                    perfilesEntities?.map { perfil ->
                        perfil.copy(id = jsonResumeId, basicoId = basicoId)
                    }?.let {
                        logger.info("save: PerfilesEntities={}", it)
                        val perfilIds = perfilDAO.insertPerfiles(it)
                        responses.add(RepositoryResponse.Success(perfilIds))
                    } ?: run {
                        logger.warn("save: PerfilesEntities is null")
                    }
                } ?: run {
                    logger.warn("save: BasicoEntity is null")
                }

                // insertamos las certificaciones
                certificacionEntities?.map { certificacion ->
                    certificacion.copy(resumeOwnerId = jsonResumeId)
                }?.let { certificaciones ->
                    logger.info("save: CertificacionesEntities={}", certificaciones)
                    val certificadoIds = certificacionDAO.insertCertificados(certificaciones)
                    responses.add(RepositoryResponse.Success(certificadoIds))
                } ?: run {
                    logger.warn("save: CertificacionesEntities is null")
                }

                // insertamos las educaciones
                educacionEntities?.map { educacion ->
                    educacion.copy(resumeOwnerId = jsonResumeId)
                }?.let { educaciones ->
                    logger.info("save: EducacionesEntities={}", educaciones)
                    val educacionIds = educacionDAO.insertEducaciones(educaciones)
                    responses.add(RepositoryResponse.Success(educacionIds))
                } ?: run {
                    logger.warn("save: EducacionEntity is null")
                }

                // insertamos las habilidades
                habilidadEntities?.map { habilidad ->
                    habilidad.copy(resumeOwnerId = jsonResumeId)
                }?.let { habilidades ->
                    logger.info("save: HabilidadesEntities={}", habilidades)
                    val habilidadIds = habilidadDAO.insertHabilidades(habilidades)
                    responses.add(RepositoryResponse.Success(habilidadIds))
                } ?: run {
                    logger.warn("save: HabilidadEntity is null")
                }

                // insertamos los idiomas
                idiomaEntities?.map { idioma ->
                    idioma.copy(resumeOwnerId = jsonResumeId)
                }?.let { idiomas ->
                    logger.info("save: IdiomasEntities={}", idiomas)
                    val idiomaIds = idiomaDAO.insertIdiomas(idiomas)
                    responses.add(RepositoryResponse.Success(idiomaIds))
                } ?: run {
                    logger.warn("save: IdiomaEntity is null")
                }

                // insertamos los intereses
                interesEntities?.map { interes ->
                    interes.copy(resumeOwnerId = jsonResumeId)
                }?.let { intereses ->
                    logger.info("save: InteresesEntities={}", intereses)
                    val interesIds = interesDAO.insertIntereses(intereses)
                    responses.add(RepositoryResponse.Success(interesIds))
                } ?: run {
                    logger.warn("save: InteresEntity is null")
                }

                // insertamos los premios
                premiosEntities?.map { premio ->
                    premio.copy(resumeOwnerId = jsonResumeId)
                }?.let { premios ->
                    logger.info("save: PremiosEntities={}", premios)
                    val premioIds = premioDAO.insertPremios(premios)
                    responses.add(RepositoryResponse.Success(premioIds))
                } ?: run {
                    logger.warn("save: PremioEntity is null")
                }

                // insertamos los proyectos
                proyectoEntities?.map { proyecto ->
                    proyecto.copy(resumeOwnerId = jsonResumeId)
                }?.let { proyectos ->
                    logger.info("save: ProyectosEntities={}", proyectos)
                    val proyectoIds = proyectoDAO.insertProyectos(proyectos)
                    responses.add(RepositoryResponse.Success(proyectoIds))
                } ?: run {
                    logger.warn("save: ProyectoEntity is null")
                }

                // insertamos las publicaciones
                publicacionEntities?.map { publicacion ->
                    publicacion.copy(resumeOwnerId = jsonResumeId)
                }?.let { publicaciones ->
                    logger.info("save: PublicacionesEntities={}", publicaciones)
                    val publicacionIds = publicacionDAO.insertPublicaciones(publicaciones)
                    responses.add(RepositoryResponse.Success(publicacionIds))
                } ?: run {
                    logger.warn("save: PublicacionEntity is null")
                }

                // insertamos las referencias
                referenciaEntities?.map { referencia ->
                    referencia.copy(resumeOwnerId = jsonResumeId)
                }?.let { referencias ->
                    logger.info("save: ReferenciasEntities={}", referencias)
                    val referenciaIds = referenciaDAO.insertReferencias(referencias)
                    responses.add(RepositoryResponse.Success(referenciaIds))
                } ?: run {
                    logger.warn("save: ReferenciaEntity is null")
                }

                // insertamos los trabajos
                trabajoEntities?.map { trabajo ->
                    trabajo.copy(resumeOwnerId = jsonResumeId)
                }?.let { trabajos ->
                    logger.info("save: TrabajosEntities={}", trabajos)
                    val trabajoIds = trabajoDAO.insertTrabajos(trabajos)
                    responses.add(RepositoryResponse.Success(trabajoIds))
                } ?: run {
                    logger.warn("save: TrabajoEntity is null")
                }

                // insertamos los voluntariados
                voluntariadoEntities?.map { voluntariado ->
                    voluntariado.copy(resumeOwnerId = jsonResumeId)
                }?.let { voluntariados ->
                    logger.info("save: VoluntariadosEntities={}", voluntariados)
                    val voluntariadoIds = voluntariadoDAO.insertVoluntariados(voluntariados)
                    responses.add(RepositoryResponse.Success(voluntariadoIds))
                } ?: run {
                    logger.warn("save: VoluntariadoEntity is null")
                }
            }
        } catch (e: Exception) {
            logger.error("save: Error saving jsonResumeWrapperEntity {}", e.message)
            responses.clear()
            responses.add(getRepositoryResponseFromException(e))
        }

        // Retornamos la respuesta.
        logger.info("save: Transaction finished with responses={}", responses)
        return responses
    }
}