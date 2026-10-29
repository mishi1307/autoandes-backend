# AutoAndes Backend

API REST independiente para el taller mecánico AutoAndes. Implementada con Java 21, Spring Boot 4, Oracle, Flyway y JWT; la SPA Angular está en el proyecto hermano utoandes-frontend.

## Requisitos

- JDK 21
- Oracle Database Free/XE accesible en localhost
- Maven Wrapper incluido

## Preparar Oracle

Crea un usuario de aplicación (ajusta el tablespace si tu instalación no usa USERS):

`sql
CREATE USER autoandes IDENTIFIED BY "AutoAndes2026*";
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE TRIGGER TO autoandes;
ALTER USER autoandes QUOTA UNLIMITED ON USERS;
`

El esquema y los datos de demostración se crean automáticamente con Flyway al iniciar. La migración carga 6 clientes, 8 servicios, 8 vehículos y 4 órdenes de ejemplo. Una de las órdenes queda anulada; AAA-111 tiene el kilometraje inicial de 45 000 km.

## Ejecutar

Desde esta carpeta, en PowerShell:

`powershell
 = "jdbc:oracle:thin:@localhost:1521/FREEPDB1"
 = "autoandes"
 = "AutoAndes2026*"
.mvnw.cmd spring-boot:run
`

El perfil predeterminado es dev. En ese perfil se crean cuatro usuarios de práctica, solo si no existen:

| Usuario | Rol | Contraseña inicial |
|---|---|---|
| admin | ADMIN | Auto2026* |
| asesor | ASESOR | Auto2026* |
| jefe | JEFE_TALLER | Auto2026* |
| inactivo | ASESOR (deshabilitado) | Auto2026* |

Puedes cambiar la contraseña de semilla mediante AUTOANDES_SEED_PASSWORD. En despliegues fuera de práctica, configura un secreto JWT propio de al menos 32 bytes en AUTOANDES_JWT_SECRET y no uses el perfil dev.

## API

- API base: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI: http://localhost:8080/v3/api-docs
- Colección Postman: postman/AutoAndes-API.postman_collection.json

La colección guarda automáticamente el JWT al ejecutar el login dmin. También puedes iniciar sesión con sesor o jefe y reemplazar la variable 	oken desde la respuesta.

## Módulos

- Autenticación JWT, consulta de usuario y permisos.
- Catálogo de servicios: consulta, alta, edición, baja lógica y restricciones por uso.
- Clientes de solo lectura y administración de vehículos.
- Órdenes de servicio transaccionales, detalle y anulación con motivo.
- Reporte de ingresos por servicio para órdenes registradas.
- Validación centralizada de solicitudes y respuestas de error HTTP.