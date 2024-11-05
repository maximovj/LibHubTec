# AppLibhubtec

Este es un proyecto de Angular

# Comandos para arrancar el proyecto

1) Instalar las dependencias

```
$ npm install
```

2) Arrancar el proyecto Angular

```
$ ng serve -o
```

# Comandos para arrancar el proyecto usando Dockerfile

Construir el contenedor de Angular, con el sig. comandos:

```shell
$ docker build --no-cache -f Dockerfile.angular -t img-libhubtec-angular .
```

Ejecutar el contenedor de Angular, con el sig. comandos:

```shell
$ docker run -d -p 4200:80 --name run-libhubtec-angular img-libhubtec-angular
```