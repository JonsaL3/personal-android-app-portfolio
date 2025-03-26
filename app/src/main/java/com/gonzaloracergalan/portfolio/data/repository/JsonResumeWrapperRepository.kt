package com.gonzaloracergalan.portfolio.data.repository

import androidx.room.withTransaction
import com.gonzaloracergalan.portfolio.data.db.PortfolioRoomDatabase
import com.gonzaloracergalan.portfolio.data.db.calculated.ActiveResumeSectionsCalculated
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
import com.gonzaloracergalan.portfolio.data.dt.dto.JsonResumeWrapperDTO
import com.gonzaloracergalan.portfolio.domain.model.Section
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class JsonResumeWrapperRepository : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("JsonResumeWrapperRepository")
    }

    private val db: PortfolioRoomDatabase by inject()

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

    val currentSectionsFlow: Flow<Set<Section>> = jsonResumeWrapperDAO
        .getCurrentActiveResumeSections().map {
            it.sections.map { section ->
                // No hago valueOf para mantener desacoplamiento
                when (section) {
                    ActiveResumeSectionsCalculated.Section.BASICO -> Section.BASICO
                    ActiveResumeSectionsCalculated.Section.CERTIFICADOS -> Section.CERTIFICADOS
                    ActiveResumeSectionsCalculated.Section.EDUCACION -> Section.EDUCACION
                    ActiveResumeSectionsCalculated.Section.HABILIDAD -> Section.HABILIDAD
                    ActiveResumeSectionsCalculated.Section.IDIOMA -> Section.IDIOMA
                    ActiveResumeSectionsCalculated.Section.INTERES -> Section.INTERES
                    ActiveResumeSectionsCalculated.Section.PREMIO -> Section.PREMIO
                    ActiveResumeSectionsCalculated.Section.PROYECTO -> Section.PROYECTO
                    ActiveResumeSectionsCalculated.Section.PUBLICACION -> Section.PUBLICACION
                    ActiveResumeSectionsCalculated.Section.REFERENCIA -> Section.REFERENCIA
                    ActiveResumeSectionsCalculated.Section.TRABAJO -> Section.TRABAJO
                    ActiveResumeSectionsCalculated.Section.VOLUNTARIADO -> Section.VOLUNTARIADO
                }
            }.toSet()
        }

    suspend fun setCurrentResumeId(resumeId: Long): Boolean {
        logger.trace("setCurrentResumeId: resumeId={}", resumeId)

        val success = try {
            db.withTransaction {
                logger.info("setCurrentResumeId: Setting all current resumes to false")
                jsonResumeWrapperDAO.setAllCurrentToFalse()
                logger.info("setCurrentResumeId: Setting current resume ($resumeId) to true")
                jsonResumeWrapperDAO.setCurrentResume(resumeId) > 0
            }
        } catch (e: Exception) {
            logger.error("setCurrentResumeId: Error setting current resumeId={}", resumeId)
            false
        }

        logger.info("setCurrentResumeId: response={}", success)
        return success
    }

    suspend fun save(
        resumeId: Long = 0,
        dto: JsonResumeWrapperDTO
    ): Int? {
        logger.trace("save: resumeId={}, DTO={}", resumeId, dto)

        // val id de la entidad que se inserta, sera la que devolvamos
        var idResponse: Int? = null

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

        // procedemos con la inserccion de los elementos, en este caso no podremos usar
        // runTransactionalRoomOperation, deberemos hacerlo a mano, porque necesitamos guardarnos
        // el id del jsonResumeWrapperEntity para poder relacionar los elementos con él.
        try {
            idResponse = db.withTransaction {
                // insertamos el id del jsonresume
                val jsonResumeId = jsonResumeWrapperDAO.insertResume(jsonResumeWrapperEntity)

                // insertamos lo basico con su lista de perfiles
                basicoEntity?.let { basico ->
                    logger.info("save: BasicoEntity={}", basico)
                    val mBasicoEntity = basico.copy(resumeOwnerId = jsonResumeId)
                    val basicoId = basicoDAO.insertBasico(mBasicoEntity)
                    perfilesEntities?.map { perfil ->
                        perfil.copy(id = jsonResumeId, basicoId = basicoId)
                    }?.let {
                        logger.info("save: PerfilesEntities={}", it)
                        perfilDAO.insertPerfiles(it)
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
                    certificacionDAO.insertCertificados(certificaciones)
                } ?: run {
                    logger.warn("save: CertificacionesEntities is null")
                }

                // insertamos las educaciones
                educacionEntities?.map { educacion ->
                    educacion.copy(resumeOwnerId = jsonResumeId)
                }?.let { educaciones ->
                    logger.info("save: EducacionesEntities={}", educaciones)
                    educacionDAO.insertEducaciones(educaciones)
                } ?: run {
                    logger.warn("save: EducacionEntity is null")
                }

                // insertamos las habilidades
                habilidadEntities?.map { habilidad ->
                    habilidad.copy(resumeOwnerId = jsonResumeId)
                }?.let { habilidades ->
                    logger.info("save: HabilidadesEntities={}", habilidades)
                    habilidadDAO.insertHabilidades(habilidades)
                } ?: run {
                    logger.warn("save: HabilidadEntity is null")
                }

                // insertamos los idiomas
                idiomaEntities?.map { idioma ->
                    idioma.copy(resumeOwnerId = jsonResumeId)
                }?.let { idiomas ->
                    logger.info("save: IdiomasEntities={}", idiomas)
                    idiomaDAO.insertIdiomas(idiomas)
                } ?: run {
                    logger.warn("save: IdiomaEntity is null")
                }

                // insertamos los intereses
                interesEntities?.map { interes ->
                    interes.copy(resumeOwnerId = jsonResumeId)
                }?.let { intereses ->
                    logger.info("save: InteresesEntities={}", intereses)
                    interesDAO.insertIntereses(intereses)
                } ?: run {
                    logger.warn("save: InteresEntity is null")
                }

                // insertamos los premios
                premiosEntities?.map { premio ->
                    premio.copy(resumeOwnerId = jsonResumeId)
                }?.let { premios ->
                    logger.info("save: PremiosEntities={}", premios)
                    premioDAO.insertPremios(premios)
                } ?: run {
                    logger.warn("save: PremioEntity is null")
                }

                // insertamos los proyectos
                proyectoEntities?.map { proyecto ->
                    proyecto.copy(resumeOwnerId = jsonResumeId)
                }?.let { proyectos ->
                    logger.info("save: ProyectosEntities={}", proyectos)
                    proyectoDAO.insertProyectos(proyectos)
                } ?: run {
                    logger.warn("save: ProyectoEntity is null")
                }

                // insertamos las publicaciones
                publicacionEntities?.map { publicacion ->
                    publicacion.copy(resumeOwnerId = jsonResumeId)
                }?.let { publicaciones ->
                    logger.info("save: PublicacionesEntities={}", publicaciones)
                    publicacionDAO.insertPublicaciones(publicaciones)
                } ?: run {
                    logger.warn("save: PublicacionEntity is null")
                }

                // insertamos las referencias
                referenciaEntities?.map { referencia ->
                    referencia.copy(resumeOwnerId = jsonResumeId)
                }?.let { referencias ->
                    logger.info("save: ReferenciasEntities={}", referencias)
                    referenciaDAO.insertReferencias(referencias)
                } ?: run {
                    logger.warn("save: ReferenciaEntity is null")
                }

                // insertamos los trabajos
                trabajoEntities?.map { trabajo ->
                    trabajo.copy(resumeOwnerId = jsonResumeId)
                }?.let { trabajos ->
                    logger.info("save: TrabajosEntities={}", trabajos)
                    trabajoDAO.insertTrabajos(trabajos)
                } ?: run {
                    logger.warn("save: TrabajoEntity is null")
                }

                // insertamos los voluntariados
                voluntariadoEntities?.map { voluntariado ->
                    voluntariado.copy(resumeOwnerId = jsonResumeId)
                }?.let { voluntariados ->
                    logger.info("save: VoluntariadosEntities={}", voluntariados)
                    voluntariadoDAO.insertVoluntariados(voluntariados)
                } ?: run {
                    logger.warn("save: VoluntariadoEntity is null")
                }
                idResponse
            }
        } catch (e: Exception) {
            logger.error("save: Error saving jsonResumeWrapperEntity {}", e.message)
        }

        // Retornamos la respuesta.
        logger.info("save: Transaction responseInsertedResumeId={}", idResponse)
        return idResponse
    }
}