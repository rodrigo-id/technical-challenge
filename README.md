# Acortador de url

## Desafio técnico, que hace la api

- Dada una URL larga, mi servicio me tiene que devolver una URL corta.
- Dada una URL corta, mi siervicio me tiene que devolver la URL larga original.
- Puedan obtenerse estadísticas de las URLs que utilizan este servicio.
- Puedan manejarse solicitudes a gran escala.
- El 90% de todas las solicitudes puedan responderse en menos de 10 ms.
- Puedan borrarse las URLs cortas necesarias.
- Y lógicamente, que el usuario navegue hacia la URL larga cuando ingresa una url corta válida
en su navegador :)

## SetUp

- Java 11
- Gradle 7.6.1
- Redis 6.2.2
- Spring boot 2.7.8
- Spring framework 5.2.3

## Como levantar el proyecto

+ Con Docker y docker-compose: 

```sh
trabajando en ello....
```

+ Sin docker

```sh
$  git clone https://github.com/rodrigo-id/technical-challenge.git
```
    - El proyecto utiliza una BD embebida H2 solo por efectos del desafio.
    - Debes levantar en tu local una instancia de redis ya que se utiliza para las tareas de cache:
        - Para mac: https://redis.io/docs/getting-started/installation/install-redis-on-mac-os/
        - Para windows: https://developer.redis.com/create/windows/
        - La api esta configurada con el puerto por defecto de redis. port=6379
    - Puedes utilizar el IDE de tu preferencia para hacer ver el codigo o levcantar la aplicacion, 
      tambien puedes hacer correr la app mediante los siguientes comandos

```sh
$  ./gradlew clean build
$  java -jar build/libs/technical-challenge-0.0.1-SNAPSHOT.jar 
```
    - Solo recuerda tener una instancia de redis corriendo en tu local :D

# Funcionamiento de los endpoints

- La api se encuentra documentada por medio de swagger, asi que para obtener la información
  de los endpoints puedes consultar la siguiente ruta, cuando la api este corriendo:
  - localhost:8080/swagger-ui.html

