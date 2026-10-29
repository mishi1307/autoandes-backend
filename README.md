# AutoAndes Backend

API REST independiente para el taller mecánico AutoAndes. Está implementada con Java 21, Spring Boot 4, Oracle, Flyway y JWT. La aplicación Angular se mantiene en el proyecto separado `autoandes-frontend`.

## Requisitos

- JDK 21
- Oracle Database con el servicio `XEPDB1` disponible en `localhost:1522`
- Maven Wrapper incluido

## Preparar Oracle

Conéctate como administrador a `XEPDB1` y crea el usuario de la aplicación:

```sql
CREATE USER AUTOANDESDB IDENTIFIED BY "123456";
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE TRIGGER TO AUTOANDESDB;
ALTER USER AUTOANDESDB QUOTA UNLIMITED ON USERS;
```

El perfil `dev` ya usa `AUTOANDESDB` / `123456`. Puedes cambiar los valores con `AUTOANDES_DB_URL`, `AUTOANDES_DB_USERNAME` y `AUTOANDES_DB_PASSWORD`.

Flyway crea el esquema y carga datos de demostración al iniciar. La migración incluye 6 clientes, 8 servicios, 8 vehículos y 4 órdenes de ejemplo.

## Ejecutar

Desde esta carpeta, en PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

El perfil predeterminado es `dev`. Para elegir un perfil diferente, configura `SPRING_PROFILES_ACTIVE`. En producción se requieren `AUTOANDES_DB_URL`, `AUTOANDES_DB_USERNAME`, `AUTOANDES_DB_PASSWORD` y `AUTOANDES_JWT_SECRET`; no uses la contraseña ni el secreto del perfil de desarrollo.

En desarrollo se crean cuatro usuarios de práctica si todavía no existen:

| Usuario | Rol | Contraseña inicial |
|---|---|---|
| admin | ADMIN | Auto2026* |
| asesor | ASESOR | Auto2026* |
| jefe | JEFE_TALLER | Auto2026* |
| inactivo | ASESOR (deshabilitado) | Auto2026* |

Puedes cambiar la contraseña de semilla con `AUTOANDES_SEED_PASSWORD`.

## API

- API base: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI: http://localhost:8080/v3/api-docs
- Colección Postman: `postman/AutoAndes-API.postman_collection.json`

La colección guarda automáticamente el JWT al ejecutar el login. También puedes iniciar sesión con `asesor` o `jefe` y reemplazar la variable `token` por el valor recibido.

## Módulos

- Autenticación JWT, consulta de usuario y permisos.
- Catálogo de servicios: consulta, alta, edición, baja lógica y restricciones por uso.
- Clientes de solo lectura y administración de vehículos.
- Órdenes de servicio transaccionales, detalle y anulación con motivo.
- Reporte de ingresos por servicio para órdenes registradas.
- Validación centralizada de solicitudes y respuestas de error HTTP.


