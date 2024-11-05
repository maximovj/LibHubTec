# LibHubTec

Este es un proyecto que consiste en crear una biblioteca virtual para una universidad.

Herramientas o técnologias que se van a usar:

* moonshine-laravel 

Para crear la parte del Panel Administrador, con un CRUD para libros, usuarios, alumnos, autorizaciones, etc. 

```plain
Requisitos
* php >= 8.1
* composer >= 2.4.1
* laravel >= 11.9
* moonshine-laravel >= 2.22
* mysql >= v5.2
    * database: libhubtec_db
    * collaction: utf8mb4  
    * collaction: utf8mb4_spanish2_ci
```

Sitio de servicio: [http//:localhost:8000](http://localhost:8000)

Puerto de servicio: 8000

* Angular 

Para crear la parte de FrontEnd de la biblioteca virtual para la universidad.

```plain
Requisitos
* Node >= 20.11.1
* npm >= 9.6.2
    * Angular >= 18.2.0
    * PrimeNG >= 17.18.11
    * PrimeIcons >= 7.0.0
    * PrimeFlex >= 3.3.1
```

Sitio de servicio: [http://localhost:4200](http://localhost:4200)

Puerto de servicio: 4200

* SpringBoot

Para crear la parte de BackEnd, desarrollar un API para consumir en Angular y moonshine-laravel

```plain
Requisitos
* java openjdk >= 17.0.2
* Apache Maven >= 3.6.3
* spring boot >= 3.3.5
* Spring Dependencias
    * MySQL Driver
    * Spring Data JPA
    * Spring Boot DevTools
    * Spring Web
    * Spring Data JDBC
* mysql >= v5.2
    * database: libhubtec_db
    * collaction: utf8mb4  
    * collaction: utf8mb4_spanish2_ci
```

Sitio de servicio: [http://localhost:5800](http://localhost:5800)

Puerto de servicio: 5800

# Requisitos

* Node >= v20.11.1
* pnpm >= v9.9.0
* Laravel >= 11.x
* PHP >=8.1
* moonshine-laravel 
* Angular >= 17.x
* Spring Boot 3
* Apache Maven >= 3.6.3
* jdk >= 17.0.2
* jre >= 17.0.11
* MySQL v5.7