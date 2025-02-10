#######################
Proyecto Android
#######################

Este proyecto utilizé tecnologías modernas para crear una aplicación robusta, escalable y segura, diseñada con las mejores prácticas en mente.

***********************
Tecnologías Utilizadas
***********************

- **Jetpack Compose**: Construcción de interfaces de usuario declarativas.
- **Kotlin**: Lenguaje principal del desarrollo.
- **Room**: Persistencia de datos local con una base de datos SQLite.
- **Ktor**: Cliente HTTP para comunicación eficiente con APIs.
- **SSL Pinning**: Aumenta la seguridad de las conexiones al servidor.
- **Clean Architecture**: Divide la aplicación en capas domain - data - app.
- **MVVM (Model-View-ViewModel)**: Patrón de arquitectura adoptado para la separación de responsabilidades.
- **Hilt**: Inyección de dependencias basada en el patrón Singleton.
- **Lottie**: Para animaciones fluidas y atractivas.
- **Compose Navigation**: Navegación declarativa entre pantallas con paso de objetos y clases.
- **Entre otras**

***********************
Arquitectura del Proyecto
***********************

El proyecto sigue el enfoque de **Clean Architecture**, organizando las capas de la siguiente manera:

- **Capa de Datos (Data Layer)**: Maneja las fuentes de datos (APIs y Room).
- **Capa de Dominio (Domain Layer)**: Contiene la lógica de negocio central y los casos de uso.
- **Capa de Presentación (Presentation Layer)**: Implementa la UI y gestiona la lógica de presentación con ViewModels.

***********************
Seguridad
***********************

Se ha implementado **SSL Pinning** para proteger la comunicación con el servidor contra ataques man-in-the-middle, asegurando la integridad y confidencialidad de los datos.

***********************
Navegación
***********************

La navegación entre pantallas se realiza mediante **Compose Navigation**, aprovechando la flexibilidad de pasar objetos y clases directamente.

***********************
Animaciones
***********************

Para enriquecer la experiencia de usuario, se han integrado animaciones mediante **Lottie**, optimizando tanto el rendimiento como la estética.

***********************
Requisitos del Servidor
***********************

Este proyecto requiere **Android API 21** o superior. Asegúrate de utilizar una versión reciente de Android Studio para evitar problemas de compatibilidad.

***********************
Contacto
***********************

Si tienes preguntas o sugerencias, no dudes en abrir un **issue** en el repositorio o contactarte con el equipo de desarrollo.
