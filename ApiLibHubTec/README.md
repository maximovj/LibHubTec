# Api LibHubTec

Este es un proyecto de Spring Boot v3 con JDK 17 implementa  JWT en la capa de Spring Security.

**NOTA**
- Para la utenticación (/v1/auth/authenticated) se requiere el email y contraseña
- El token generado agrega como subject el id el usuario / cuenta
- Para válidar (/v1/auth/verify-token) el token se require las cabeceras (Headers) con `Authorization`  

# Comandos

Comandos para iniciar el proyecto de Spring Boot

Arrancar el proyecto

```shell
$ mvn spring-boot:run
```

Antes de arrancar el proyecto, puedes limpiar y compilar el proyecto con el siguiente comando:

```shell
$ mvn clean install
$ mvn clean install -DskipTests
```

Ver la lista de dependencias para el proyecto

```shell
$ mvn dependency:list
```

Este comando muestra si hay nuevas versiones disponibles para las dependencias de tu proyecto.

```shell
$ mvn versions:display-dependency-updates
```

Este comando limpia el proyecto, valida la configuración y luego instala las dependencias 

necesarias y el artefacto final en tu repositorio local.

```shell
$ mvn clean validate install
```

Este comando genera un sitio web estático para tu proyecto, que incluye documentación sobre dependencias, 

cobertura de pruebas, informes de errores, etc. El sitio se genera en el directorio target/site.


```shell
$ mvn site
```

# Comandos para arrancar el proyecto usando Dockerfile

Construir el contenedor de Angular, con el sig. comandos:

```shell
$ docker build -f Dockerfile.springboot -t img-springboot-3 .
```

Ejecutar el contenedor de Angular, con el sig. comandos:

```shell
$ docker run -d -p 5800:5800 img-springboot-3
# o también
$ docker run -it -p 5800:5800 img-springboot-3
```