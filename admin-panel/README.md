# AdminPanel

Este admin panel es creado usando Moonshine Laravel

# Requisitos

* php => 8.1
* composer => 2.4.1
* laravel >= 11.9
* moonshine-laravel => 2.22
* mysql => v5.2
    * database: libhubtec_db
    * collaction: utf8mb4  
    * collaction: utf8mb4_spanish2_ci

# Arranque del AdminPanel

1) Definir la variables de entorno de `.env`, creando una copia de archivo `.env.example`

```shell
$ cp ./.env.example ./.env
```

2) Ejecuta el siguiente comando para descargar dependencias usando `composer`

```shell
$ composer install
```

3) Generar una clave de aplicación

```shell
$ php artisan key:generate
```

4) Ejecutar migraciones para crear tablas

```shell
$ php artisan migrate
```

5) Crear un usuario administrador para el Admin Panel

```shell
$ php artisan moonshine:user
```

6) Insertar datos a la tabla de libros

```shell
$ php artisan db:seed --class=BooksTableSeeder
```

7) Crear una carpeta simbólica de storage a la carpeta public

```shell
$ php artisan storage:link
```

8) Lanzar el portal Admin Panel

```shell
$ php artisan serve
```
