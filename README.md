# Catálogo de Mercadería - Casa de Electricidad

Este proyecto implementa un sistema de gestión de catálogo para una casa de electricidad usando Spring Boot. El sistema permite administrar un catálogo de productos sin necesidad de controlar el stock, centrándose en la visualización y gestión de artículos disponibles.

## Características Principales

### Usuarios y Roles
- **Registro de usuarios**: Nuevos usuarios se registran automáticamente con rol USER
- **Roles**: Sistema con dos roles (ADMIN, USER)
- **Usuario administrador**: Configurado desde la base de datos

### Gestión de Artículos
- **Alta de artículos**: Creación de nuevos productos en el catálogo
- **Modificación de artículos**: Actualización de información de productos existentes
- **Listado completo**: Visualización del catálogo para usuarios registrados
- **Código autogenerado**: Numeración automática de artículos (1, 2, 3...)

### Gestión de Fábricas
- **Registro de fabricantes**: Asociación de artículos con sus respectivos fabricantes

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal de desarrollo
- **Spring Data JPA**: Persistencia y acceso a datos
- **Spring Security**: Autenticación y autorización de usuarios
- **Thymeleaf**: Motor de plantillas para vistas
- **MySQL**: Base de datos (configurable)
- **Lombok**: Reducción de código repetitivo
- **Bootstrap**: Diseño de interfaz de usuario

## Configuración Inicial

### Requisitos
- Java 17 o superior
- Maven 3.6 o superior
- MySQL

