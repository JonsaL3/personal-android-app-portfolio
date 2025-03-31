package com.gonzaloracergalan.portfolio.data.util

import com.gonzaloracergalan.portfolio.data.dt.dto.BasicoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.CertificadoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.EducacionDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.HabilidadDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.IdiomaDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.InteresDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.JsonResumeWrapperDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.PerfilDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.PremioDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.ProyectoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.PublicacionDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.ReferenciaDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.TrabajoDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.UbicacionDTO
import com.gonzaloracergalan.portfolio.data.dt.dto.VoluntariadoDTO
import com.gonzaloracergalan.portfolio.data.repository.JsonResumeWrapperRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Example : KoinComponent {
    private val saver: JsonResumeWrapperRepository by inject()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val exampleDTO = JsonResumeWrapperDTO(
                basico = BasicoDTO(
                    correo = "juan.perez@example.com",
                    etiqueta = "Desarrollador Full Stack",
                    imagen = "https://example.com/avatar.jpg",
                    nombre = "Juan Pérez",
                    perfiles = listOf(
                        PerfilDTO("LinkedIn", "https://linkedin.com/in/juanperez", "Perfil profesional en LinkedIn"),
                        PerfilDTO("GitHub", "https://github.com/juanperez", "Repositorio de proyectos y código abierto")
                    ),
                    resumen = "Soy un desarrollador full stack con más de 8 años de experiencia en el sector tecnológico. " +
                            "He participado en proyectos que abarcan desde aplicaciones móviles hasta sistemas empresariales complejos.\n\n" +
                            "Mi pasión es crear soluciones eficientes y escalables, y siempre estoy en búsqueda de nuevos desafíos " +
                            "que me permitan crecer profesionalmente. Me adapto rápidamente a las nuevas tecnologías y metodologías, " +
                            "con un enfoque pragmático y orientado a resultados.",
                    telefono = "+34 600 123 456",
                    ubicacion = UbicacionDTO(
                        codigoPostal = "28001",
                        direccion = "Calle de la Luna, 12, Madrid",
                        region = "Comunidad de Madrid"
                    ),
                    url = "https://juanperez.dev"
                ),
                certificados = listOf(
                    CertificadoDTO("Oracle Certified Professional", "Oracle", "Certificación en Java SE 8", "2020"),
                    CertificadoDTO("AWS Solutions Architect", "Amazon Web Services", "Arquitectura de soluciones en la nube", "2021"),
                    CertificadoDTO("Scrum Master", "Scrum Alliance", "Gestión ágil de proyectos", "2019")
                ),
                educacion = listOf(
                    EducacionDTO(
                        area = "Ingeniería Informática",
                        calificacion = "8.5/10",
                        cursos = listOf("Programación Avanzada", "Estructuras de Datos", "Algoritmos"),
                        fechaInicio = "2010-09",
                        fechaFin = "2014-06",
                        institucion = "Universidad Complutense de Madrid",
                        tipoEstudio = "Grado",
                        url = "https://www.ucm.es"
                    ),
                    EducacionDTO(
                        area = "Máster en Desarrollo de Software",
                        calificacion = "9.0/10",
                        cursos = listOf("Arquitectura de Software", "Microservicios", "DevOps"),
                        fechaInicio = "2015-09",
                        fechaFin = "2016-06",
                        institucion = "Universidad Politécnica de Madrid",
                        tipoEstudio = "Máster",
                        url = "https://www.upm.es"
                    )
                ),
                habilidades = listOf(
                    HabilidadDTO(
                        "Lenguajes de Programación",
                        "Dominio de diversos lenguajes esenciales para el desarrollo moderno",
                        listOf("Java", "Kotlin", "Python", "JavaScript")
                    ),
                    HabilidadDTO(
                        "Frameworks y Librerías",
                        "Experiencia en el uso de frameworks robustos para el desarrollo ágil",
                        listOf("Spring Boot", "React", "Angular", "Django")
                    ),
                    HabilidadDTO(
                        "Herramientas de Desarrollo",
                        "Conocimiento en herramientas que facilitan la integración y despliegue continuo",
                        listOf("Git", "Docker", "Jenkins", "Kubernetes")
                    )
                ),
                idiomas = listOf(
                    IdiomaDTO("Español", "Nativo"),
                    IdiomaDTO("Inglés", "Avanzado"),
                    IdiomaDTO("Francés", "Intermedio")
                ),
                intereses = listOf(
                    InteresDTO("Tecnología", listOf("Inteligencia Artificial", "Blockchain", "Internet de las Cosas")),
                    InteresDTO("Deportes", listOf("Fútbol", "Ciclismo", "Escalada"))
                ),
                premios = listOf(
                    PremioDTO("Premio Innovación Tecnológica", "Tech Awards", "Reconocimiento por el desarrollo de soluciones innovadoras", "2022"),
                    PremioDTO("Mejor Proyecto Open Source", "DevCon", "Reconocimiento a la contribución en proyectos de código abierto", "2021"),
                    PremioDTO("Líder del Año", "Tech Leaders", "Premio por liderazgo y gestión de equipos de alto rendimiento", "2020")
                ),
                proyectos = listOf(
                    ProyectoDTO(
                        descripcion = "Desarrollo de una plataforma de e-commerce que integra pagos electrónicos y gestión de inventarios en tiempo real, con escalabilidad para miles de usuarios concurrentes.",
                        fechaInicio = "2018-01",
                        fechaFin = "2019-12",
                        nombre = "E-Shop Platform",
                        url = "https://github.com/juanperez/eshop-platform"
                    ),
                    ProyectoDTO(
                        descripcion = "Aplicación móvil para la gestión de reservas y eventos, implementando notificaciones push y análisis avanzado de datos de usuario para mejorar la experiencia.",
                        fechaInicio = "2020-03",
                        fechaFin = "2021-08",
                        nombre = "EventManager App",
                        url = "https://github.com/juanperez/eventmanager-app"
                    )
                ),
                publicaciones = listOf(
                    PublicacionDTO(
                        nombre = "Optimización de Microservicios en entornos Cloud",
                        url = "https://medium.com/@juanperez/microservicios-cloud",
                        editor = "Medium",
                        fechaPublicacion = "2021-05",
                        resumen = "Exploración de estrategias y mejores prácticas para optimizar microservicios en plataformas cloud, mejorando la eficiencia y escalabilidad."
                    ),
                    PublicacionDTO(
                        nombre = "Desarrollo Ágil con Scrum y Kanban",
                        url = "https://dev.to/juanperez/desarrollo-agil",
                        editor = "Dev.to",
                        fechaPublicacion = "2020-11",
                        resumen = "Análisis práctico de metodologías ágiles y su implementación en equipos de desarrollo modernos."
                    ),
                    PublicacionDTO(
                        nombre = "Introducción a Docker y Kubernetes",
                        url = "https://blog.juanperez.dev/docker-kubernetes",
                        editor = "Blog Personal",
                        fechaPublicacion = "2019-07",
                        resumen = "Guía completa para principiantes en el uso de contenedores y la orquestación de aplicaciones con Kubernetes."
                    )
                ),
                referencias = listOf(
                    ReferenciaDTO("María García", "Exjefa en proyectos tecnológicos"),
                    ReferenciaDTO("Carlos López", "Colega en el desarrollo de software"),
                    ReferenciaDTO("Ana Martínez", "Profesora universitaria y mentora"),
                    ReferenciaDTO("Luis Fernández", "Consultor y mentor en innovación tecnológica")
                ),
                trabajo = listOf(
                    TrabajoDTO(
                        fechaInicioMillis = 1546300800000, // 1 Ene 2019
                        fechaFinMillis = 1577836800000,   // 1 Ene 2020
                        logros = listOf(
                            "Implementación de microservicios escalables",
                            "Reducción del tiempo de respuesta en un 30%",
                            "Liderazgo de equipo en proyecto internacional"
                        ),
                        nombre = "Tech Solutions S.A.",
                        posicion = "Desarrollador Full Stack Senior",
                        resumen = "Responsable del diseño e implementación de soluciones web y móviles, trabajando en proyectos de gran envergadura y alta demanda.",
                        url = "https://techsolutions.example.com"
                    ),
                    TrabajoDTO(
                        fechaInicioMillis = 1514764800000, // 1 Ene 2018
                        fechaFinMillis = 1546214400000,   // 1 Ene 2019
                        logros = listOf(
                            "Desarrollo de aplicaciones móviles nativas",
                            "Integración de APIs de terceros",
                            "Optimización del rendimiento de la aplicación"
                        ),
                        nombre = "Innovatech Ltd.",
                        posicion = "Desarrollador Android",
                        resumen = "Encargado del desarrollo y mantenimiento de aplicaciones Android, colaborando estrechamente con equipos de diseño y producto.",
                        url = "https://innovatech.example.com"
                    )
                ),
                voluntariado = listOf(
                    VoluntariadoDTO(
                        fechaInicioMillis = 1609459200000, // 1 Ene 2021
                        fechaFinMillis = 1640995200000,   // 1 Ene 2022
                        logros = listOf(
                            "Organización de talleres de programación",
                            "Mentoría a jóvenes talentos",
                            "Colaboración en eventos tecnológicos"
                        ),
                        organizacion = "CoderDojo Madrid",
                        posicion = "Mentor y Voluntario",
                        resumen = "Participé en iniciativas para enseñar programación a niños y adolescentes, fomentando el interés por la tecnología y el desarrollo de habilidades digitales.",
                        url = "https://coderdojo.example.com"
                    ),
                    VoluntariadoDTO(
                        fechaInicioMillis = 1577836800000, // 1 Ene 2020
                        fechaFinMillis = 1609459200000,   // 1 Ene 2021
                        logros = listOf(
                            "Coordinación de eventos de networking",
                            "Capacitación en habilidades digitales",
                            "Asesoramiento en proyectos comunitarios"
                        ),
                        organizacion = "Tech4Good",
                        posicion = "Coordinador de Voluntariado",
                        resumen = "Colaboré en proyectos destinados a impulsar el uso de la tecnología para el bien social, organizando eventos y talleres que conectaban a profesionales y comunidades.",
                        url = "https://tech4good.example.com"
                    )
                )
            )
            saver.save(dto = exampleDTO)
        }
    }

}