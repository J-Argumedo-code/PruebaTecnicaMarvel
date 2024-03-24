# API de Marvel en Spring Boot

Este proyecto implementa una API en Java utilizando el framework Spring Boot con una arquitectura de software MVC para consumir la API pública de Marvel, que proporciona información sobre personajes, historietas y series.

# Dependecias
## 1. spring-boot-starter-data-jpa
Esta dependencia proporciona soporte para el acceso a bases de datos relacionales a través de JPA (Java Persistence API). Incluye configuraciones predefinidas para Hibernate, un framework de mapeo objeto-relacional (ORM) muy utilizado en el ecosistema de Spring Boot.

## 2. spring-boot-starter-web
Esta dependencia agrega las bibliotecas necesarias para desarrollar aplicaciones web con Spring Boot. Incluye el contenedor web embebido de Spring Boot y las dependencias necesarias para desarrollar aplicaciones web RESTful.

## 3. spring-boot-devtools
Esta dependencia proporciona herramientas de desarrollo adicionales para mejorar la experiencia de desarrollo en Spring Boot. Incluye funcionalidades como la recarga automática (live reload) de la aplicación cuando hay cambios en el código fuente, entre otras herramientas útiles para el desarrollo.

## 4. mysql-connector-j
Esta dependencia es el controlador JDBC para MySQL. Permite a la aplicación comunicarse con una base de datos MySQL utilizando el protocolo JDBC estándar de Java.

## 5. lombok
Lombok es una biblioteca que permite reducir la cantidad de código boilerplate en Java. Proporciona anotaciones que generan automáticamente getters, setters, constructores, y otros métodos comunes en clases Java, lo que simplifica el desarrollo y reduce la cantidad de código que necesitas escribir manualmente.

## 6. spring-boot-starter-test
Esta dependencia agrega las bibliotecas necesarias para escribir pruebas unitarias y de integración en aplicaciones Spring Boot. Incluye frameworks como JUnit, Mockito y Spring Test para facilitar la escritura y ejecución de pruebas.

## 7. spring-boot-starter-security
Esta dependencia agrega soporte para la seguridad en aplicaciones Spring Boot. Incluye funcionalidades como autenticación, autorización y manejo de sesiones, lo que permite proteger los endpoints de la API y controlar el acceso a recursos protegidos.

## 8. jjwt-api
Esta dependencia proporciona la API para trabajar con JWT (JSON Web Tokens). JWT es un estándar abierto (RFC 7519) que define un método compacto y seguro para la transmisión de información entre partes como un objeto JSON. Es ampliamente utilizado para la autenticación y autorización en aplicaciones web y APIs RESTful.

## 9. jjwt-impl
Esta dependencia proporciona la implementación de JWT para la generación y verificación de tokens JWT. Es una parte fundamental para trabajar con JWT en aplicaciones Spring Boot.

## 9. jjwt-jackson
Esta dependencia agrega soporte para serializar y deserializar objetos JWT utilizando la biblioteca Jackson, que es la biblioteca de serialización y deserialización JSON predeterminada en Spring Boot.

> Estas dependencias son esenciales para desarrollar una aplicación Spring Boot que utilice una base de datos MySQL, implemente seguridad con JWT y Spring Security, y realice pruebas unitarias y de integración.

# Pasos instalación
Para poder construir y ejecutar la aplicación, sigue estos pasos.

## 1. Clonar el repositorio
	git clone https://github.com/J-Argumedo-code/PruebaTecnicaMarvel.git

## 2. Abrir el proyecto
Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).

## 3. Crear la base de datos
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

INSERT INTO user (name, email, password)
VALUES ('admin', 'admin@gmail.com', 'Contraseña encriptada con BCrypt');
```
> La aplicación contiene un método para encriptar una contraseña localmente, ubicado en la clase com.marvel.security.WebSecurityConfig.java;



## 4. Configurar las credenciales de acceso

Configura las credenciales de acceso a la API de Marvel en el archivo `application.properties`

	marvel.url-ts = un_string
	marvel.url-apiKey = tu_llave_publica
	marvel.url-hash = codigo_hash

> Para más información sobre como formar el código hash, revisar Documentación de Api de Marvel en: https://developer.marvel.com/documentation/authorization

## 5. Ejecutar la aplicación

Ejecuta la aplicación de Spring Boot localmente con el método main en `PruebaTecnicaApplication.java`

## EndPoints
Una vez que la aplicación esté en funcionamiento, puedes interactuar con los distintos endpoints utilizando un cliente HTTP o una herramienta como Postman. 

### Login
Para poder consumir los EndPoints se debe realizar un proceso de Login, para obtener un token de autorización.

#### Request body

`POST /login`

    {
        "email": "admin@gmail.com",
        "password": "admin" //Ejemplo de contraseña, no debe estar encriptada
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
