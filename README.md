# API de Marvel en Spring Boot

Este proyecto implementa una API en Java utilizando el framework Spring Boot para consumir la API pública de Marvel, que proporciona información sobre personajes, historietas y series.

**Tabla de contenidos**

[TOC]

# Pasos instalación
Para poder construir y ejecutar la aplicación, sigue estos pasos.

## Clonar el repositorio
	git clone https://github.com/tu_usuario/tu_repositorio.git

## Abrir el proyecto
Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).

## Crear la base de datos
La base de datos de este proyecto ha sido desarrollada en MySql.
> Se debe hacer al menos el insert de un usuario para realizar el Login en la aplicación.

```sql
CREATE DATABASE marvel;
USE marvel;

CREATE TABLE user (
  id_user int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL UNIQUE,
  email varchar(45) NOT NULL UNIQUE,
  password varchar(200) NOT NULL
);

CREATE TABLE history (
	id_history int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user varchar(45) NOT NULL,
    action varchar(200) NOT NULL
);

INSERT INTO user (name, email, password) VALUES ('admin', 'admin@gmail.com', 'Tu contraseña encriptada con BCrypt');
```
> La aplicación contiene un método para encriptar una contraseña localmente, ubicado en la clase com.marvel.security.WebSecurityConfig.java;



## Configurar las credenciales de acceso

Configura las credenciales de acceso a la API de Marvel en el archivo `application.properties`

	marvel.url-ts = un_string
	marvel.url-apiKey = tu_llave_publica
	marvel.url-hash = codigo_hash

> Para más información sobre como formar el código hash, revisar Documentación de Api de Marvel en: https://developer.marvel.com/documentation/authorization

## Ejecutar la aplicación:

Ejecuta la aplicación Spring Boot localmente con el método main en `PruebaTecnicaApplication.java`

## EndPoints
Una vez que la aplicación esté en funcionamiento, puedes interactuar con los distintos endpoints utilizando un cliente HTTP o una herramienta como Postman. 

### Login
Para poder consumir los EndPoints se debe realizar un proceso de Login, para obtener un token de autorización.

####Request body

`POST /login`

    {
        "email": "admin@gmail.com",
        "password": "admin"
    }
> Deben ser las credenciales de un usario que exista en la base de datos

#### Response headers

	Bearer TOKEN_GENERADO

> El token generado debe ser añadido en "Authorization" de PostMan, en la opción Bearer Token


### 1. Buscar personajes de Marvel por nombre, historietas y serie
------------

#### Request
`GET /marvel/searchHero?heroName=nombre&comicId=id&serieId=id`

	http://localhost:8080/marvel/searchHero?heroName=Thor&comicId=43506&serieId=16450

#### Response
    {
        "code": "200",
        "status": "Ok",
        "copyright": "© 2024 MARVEL",
        "attributionText": "Data provided by Marvel. © 2024 MARVEL",
        "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2024 MARVEL</a>",
        "etag": "0fe3652ce296e0cf2a46931a199788253819cae4",
        "data": { ... }
    }



### 2. Obtener el listado de cómics que tiene un personaje específico

------------
#### Request
`GET /marvel/searchComicsByHeroId?heroId=id`

	http://localhost:8080/marvel/searchComicsByHeroId?heroId=1009664

#### Response
    {
        "data": {
            "results": [
                {
                    "title": "..."
                },
                {
                    "title": "..."
                },
            ]
        }
    }

### 3. Obtener la imagen y descripción de un personaje específico

------------
#### Request
`GET /marvel/getHeroDescriptionImage?heroId=id`

	http://localhost:8080/marvel/getHeroDescriptionImage?heroId=1009664

#### Response
    {
        "description": " ... ",
        "imageUrl": "http://image.jpg"
    }

### 4. Mostrar listas de cómics completas

------------
#### Request
`GET /marvel/getComics`

	http://localhost:8080/marvel/getComics

#### Response
    {
        "code": "200",
        "status": "Ok",
        "copyright": "© 2024 MARVEL",
        "attributionText": "Data provided by Marvel. © 2024 MARVEL",
        "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2024 MARVEL</a>",
        "etag": "0fe3652ce296e0cf2a46931a199788253819cae4",
        "data": { ... }
    }

### 5. Mostrar cómic filtrado por ID

------------
#### Request
`GET /marvel/getComicById?comicId=id`

	http://localhost:8080/marvel/getComicById?comicId=43506

#### Response
    {
        "code": "200",
        "status": "Ok",
        "copyright": "© 2024 MARVEL",
        "attributionText": "Data provided by Marvel. © 2024 MARVEL",
        "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2024 MARVEL</a>",
        "etag": "0fe3652ce296e0cf2a46931a199788253819cae4",
        "data": { ... }
    }

### 6. Mostrar las búsquedas que cualquier usuario haya hecho relacionadas a historietas

------------
#### Request
`GET /marvel/getComicsSearchHistory`

	http://localhost:8080/marvel/getComicsSearchHistory

#### Response
    [
        {
            "id_history": 1,
            "user": "admin",
            "action": "getComicById"
        },
        {
            "id_history": 2,
            "user": "admin",
            "action": "searchComicsByHeroId"
        },
    ]

### 7. Mostrar las búsquedas que un usuario específico ha realizado

------------
#### Request
`GET /marvel/getUserSearchHistory?user=nombre_usuario`

	http://localhost:8080/marvel/getUserSearchHistory?user=admin

#### Response
    [
        {
            "id_history": 1,
            "user": "admin",
            "action": "getComicById"
        },
        {
            "id_history": 2,
            "user": "admin",
            "action": "searchHero"
        },
    ]

### Otros aspectos

#### Seguridad
La API utiliza Spring Security y JWT para autenticación y autorización. Debes iniciar sesión para obtener un token JWT, que luego debes incluir en las cabeceras de las solicitudes posteriores.

#### Historial de búsquedas
Se registra el historial de búsquedas de los usuarios, lo que permite visualizar las búsquedas realizadas por cualquier usuario o por un usuario específico.

------------

> Con esta guía, estarás listo para comenzar a utilizar la API y explorar todas sus funcionalidades. ¡Disfruta explorando el universo de Marvel!
