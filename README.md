# **Proyecto Gestion de Empleados**
## **Descripción**
Esta es una aplicacion de sistema de usuario para controlar sus empleados
## **Tecnologías utilizadas**
- **Java**: Versión 17
- **Spring Boot**:  3.x
- **Redis**: Proveedor de almacenamiento en caché
- **Gradle**: Para la gestión de dependencias
- **Spring Data Redis**: Biblioteca para la integración con Redis
- **Prometheus**: Bibliotica de metricas
- **Azure**: Infraestructura de servicios
- **GitHub Actions**: CI/CD

## **Instalación**
1. **Clonar el repositorio**:
``` bash
   git clone https://github.com/CodeTecnologyYT/bff-manager-customer
   cd bff-manager-customer
```
2. **Configurar Ambiente**: Asegúrate de tener Redis instalado y en ejecución. Puedes usar Docker para instalarlo fácilmente:
En la carpeta [Infraestructura/Docker](https://github.com/CodeTecnologyYT/bff-manager-customer/tree/main/infrastructure/docker)
``` bash
   docker compose up -d
```
3. **Compilar y ejecutar el proyecto**:
``` bash
   ./gradlew bootRun
```
4. **Coleccion de postman**:
En la carpeta [Collectionn](https://github.com/CodeTecnologyYT/bff-manager-customer/tree/main/collections)
- Colleccion de Enviroment: Production y Localhost
- Collection de Endpoints
## **Contacto**
- **Autor**: [Bryan Rosas Quispe]
- **Correo**: [bryan.rosas.quispe@gmail.com]
- **LinkedIn**: [https://www.linkedin.com/in/bryanwrq/](https://www.linkedin.com/in/bryanwrq/)