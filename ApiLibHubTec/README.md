# Api LibHubTec

Comandos para iniciar el proyecto de Spring Boot


# Comandos

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
