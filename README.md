# Spring Reservas API

API REST para la gestión de reservas de espacios de coworking.  
Construida con **Spring Boot**, **Spring Security (JWT)** y **PostgreSQL**.

---

## Características
- Registro y autenticación de usuarios con **JWT**.
- Roles y permisos (`ADMIN`, `USER`).
- Gestión de espacios y reservas.
- Reportes de ocupación con filtros por rango de fechas Cacheados.
- Integración con Docker y docker-compose.
- Documentación con Swagger/OpenAPI.
- Manejo Global de Excepciones
- Observabilidad con Spring Boot Actuator


---

## Patrón de GOF de Comportamiento: Observer

Se aplico **Observer**, `ReservaServiceImpl` adopta el rol exclusivo de **Sujeto** o emisor. Su única responsabilidad tras procesar un pago exitoso es publicar un evento desacoplado llamado `ReservaConfirmadaEvent`.

Las tareas secundarias se trasladaron a componentes independientes que actúan como **Observadores**. Al utilizar la anotación `@EventListener`, estos observadores reaccionan de manera automática y en paralelo al evento disparado:
1. **Notificador Email:** Reacciona imprimiendo la confirmación de la reserva.
2. **Componente de Auditoría:** Registra en los logs del sistema el identificador único para monitoreo de operaciones.


---

### REQUISITOS PREVIOS 

tener Maven y Docker Desktop instalado

## Instalación y ejecución

 1. Clonar el repositorio con el siguiente comando
```bash
git clone https://github.com/<tu-usuario>/<tu-repo>.git
cd <tu-repo>
```
 2. Una vez dentro de la carpeta del proyecto ejecutar
```bash
mvn clean package -DskipTests
docker-compose up --build
```
 3. Despues que al aplicacion este corriendo se puede visitar el siguiente sitio para ver la documentacion : 
    http://localhost:8080/swagger-ui.html
 4. Observabilidad con Actuator en: http://localhost:8080/actuator/health

---
### ¿ Que se pudiera hacer con mas tiempo ?

Se pudiera haber mejorado los reportes, la funcionalidad para hacer reservas, o realizar una pipeline CICD y desplegar la app en la nube, agregar servicios verdaderos para enviar la notificacion de correo o probar el circuit breaker