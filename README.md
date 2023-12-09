# Pokédex API

Esta é uma API RESTful utilizando Java e o framework Quarkus. A aplicação permite buscar, listar, salvar e buscar detalhes de Pokémons usando a PokéAPI.

## Como executar o projeto
### Requisitos
- Docker
- Docker Compose
- Java 17 ou 21
- Maven
- Quarkus

### Instruções
1. Clone este repositório:
```
git clone https://github.com/TiagoPantoja/pokedex-api.git
```

2. Digite o comando:
```
cd pokedex-api
```
Para acessar a pasta raiz do projeto

2. Execute o Docker Compose para subir o banco de dados PostgreSQL e a aplicação Quarkus:
```
docker-compose up -d
```

3. Construa e execute a aplicação Quarkus:
```
./mvnw clean package -Pnative
docker build -f Dockerfile -t quarkus/pokemon-resource .
docker run -i --rm -p 8080:8080 quarkus/pokemon-resource
```

4. Acesse a API: http://localhost:8080.

### Endpoints
### GET /pokemon/{id}
Obtém detalhes de um Pokémon pelo ID.

**Exemplo:**
```
curl http://localhost:8080/pokemon/25
```

### GET /pokemon
Obtém a lista de todos os Pokémons.

**Exemplo:**
```
curl http://localhost:8080/pokemon
```

### POST /pokemon
Salva um novo Pokémon.

**Exemplo:**
```
curl -X POST -H "Content-Type: application/json" -d '{"name": "TestPokemon", "pokemonId": 999, "sprite": "https://example.com/image.png", "height": 1.0, "weight": 1.0}' http://localhost:8080/pokemon
```

### GET /pokemon/search?nameOrId={nameOrId}
Busca um Pokémon por nome ou ID.

**Exemplo:**
```
curl http://localhost:8080/pokemon/search?nameOrId=pikachu
```

### Testes
A aplicação inclui testes de integração para garantir o correto funcionamento dos endpoints.

Para executar os testes:
```
./mvnw test
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/pokemon-resource-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
