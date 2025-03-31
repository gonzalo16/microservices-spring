# microservices-spring
Este es el proyecto principal de una aplicación de microservicios en Spring. Es un proyecto de Spring de multimódulos que son
los microservicios. Lo he echo de esta forma para no crear tantos repositorios.

Los módulos de los que se componen son
- Eureka server
- msvc-items (Microservicio de item)
- msvc-productos (Microservicio de productos)
- msvc-gateway-server (Puerta de enlace para gestionar y enrutar las peticiones)
- eureka-server (Servidor de descubrimiento) Actua como un registro centralizado donde se registran los microservicios y se comunican
- msvc-users (Microservicio de usuarios)
- msvc-oauth (Microservicio de autorizacion)