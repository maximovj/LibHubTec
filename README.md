# LibHubTec

Este es un proyecto que consiste en crear una biblioteca virtual para una universidad.

Herramientas o técnologias que se van a usar:

* Laravel 11.x y moonshine-laravel 

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

Sitio de servicio: [http://192.168.33.99:8000](http://192.168.33.99:8000)

Puerto de servicio: 8000

* Angular 18.x 

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

Sitio de servicio: [http://192.168.33.99:4200](http://192.168.33.99:4200)

Puerto de servicio: 4200

* SpringBoot 3.x

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

Sitio de servicio: [http://192.168.33.99:5800](http://192.168.33.99:5800)

Puerto de servicio: 5800

* Mailpit

Es un servidor local para probar envios de correo electrónicos mediante STMP

Sitio de servicio: [http://192.168.33.99:8025](http://192.168.33.99:8025)

Puerto de servicio: 8025

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
* Mailpit v1.21.4

# Arrancar Vagrant como maquina virtual

**Paso 1)** Accerder a la ruta raíz (root) el proyecto, a la par de archivo `Vagrantfile`

Ejecuta el siguiente comando, desde la terminal.

```shell
$ vagrant up
```

Ejecuta el siguiente comando, desde la terminal para acceder a la máquina virtual.

```shell
$ vagrant ssh
```

<br>

**Advertencia:** Si se usa Vagrant como máquina virtual, este caja de Vagrant está configurado como una red privada, 

con dirección IP de `192.168.33.99`, por lo tanto para acceder a los serivcios de este proyecto, 

es necesario cambiar `localhost` por la dirección IP de la caja de Vagrant (y viceversa), sería de está manera:

Sitio de servicio Laravel 11.x: [http://192.168.33.99:8000](http://192.168.33.99:8000)

Sitio de servicio Angular 18.x: [http://192.168.33.99:4200](http://192.168.33.99:4200)

Sitio de servicio SpringBoot 3.x: [http://192.168.33.99:5800](http://192.168.33.99:5800)

Sitio de servicio Mailpit: [http://192.168.33.99:8025](http://192.168.33.99:8025)

<br>

**NOTA:** Si no se está usando Vagrant como máquina virtual es necesario cambiar la dirección IP (`192.168.33.99`) de la caja de Vagrant 

por `localhost`, en archivos de  Dockerfile y Docker-compose.  

# Arrancar servicios (del proyecto) usando Docker-compose

**Paso 1)** Accerder a la ruta raíz (root) el proyecto, a la par de archivo `docker-compose.yml`

Ejecuta el siguiente comando, desde la terminal.

```shell
$ cd workspace
```

**Paso 2)** Convetir el achivo `setup.sh` en un archivo ejecutable o script de linux y ejecutarló. 

Además de crear variable de entorno, instalar nodejs, y crear alias para linux

Ejecuta el siguiente comando, desde la terminal.

```shell
$ dos2unix ./setup.sh && ./setup.sh && source ~/.bashrc 
```

**Paso 3)** Construir todos los servicios de docker-compose

Se construye la imagen, contenedor y ejecuta cada servicio que compone docker-compose 

```shell
$ doc-again
```

**Paso 4)** Comienza a probar el proyecto

Felicidades ya puedes acceder a los sitios del proyecto, que son:

- Sitio de servicio Laravel 11.x: [http://192.168.33.99:8000](http://192.168.33.99:8000)

    usuario: admin@admin.com

    contraseña: password

- Sitio de servicio Angular 18.x: [http://192.168.33.99:4200](http://192.168.33.99:4200)

    usuario: victor.maximo@example.com
    
    contraseña: password

- Sitio de servicio SpringBoot 3.x: [http://192.168.33.99:5800](http://192.168.33.99:5800)

- Sitio de servicio Mailpit: [http://192.168.33.99:8025](http://192.168.33.99:8025)

<br>

# Vista previas

![preview1.jpg](/screenshots/preview_1.jpg)

![preview2.jpg](/screenshots/preview_2.jpg)

![preview3.jpg](/screenshots/preview_3.jpg)

![preview4.jpg](/screenshots/preview_4.jpg)

![preview5.jpg](/screenshots/preview_5.jpg)

![preview6.jpg](/screenshots/preview_6.jpg)

![preview7.jpg](/screenshots/preview_7.jpg)

![preview8.jpg](/screenshots/preview_8.jpg)

![preview9.jpg](/screenshots/preview_9.jpg)

![preview10.jpg](/screenshots/preview_10.jpg)

![preview11.jpg](/screenshots/preview_11.jpg)

![preview11.jpg](/screenshots/preview_12.jpg)

![preview11.jpg](/screenshots/preview_13.jpg)

![preview11.jpg](/screenshots/preview_14.jpg)
