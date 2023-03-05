# opencertificate-api project
Backend project for OpenCertificate platform.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

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

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/opencertificate-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Code style
This project uses checkstyle [plugin in maven](http://maven.apache.org/plugins/maven-checkstyle-plugin/usage.html) to mantain the default code style. See more in [project site](https://checkstyle.sourceforge.io/).

O plugin foi configurado para bloquear build com erros de estilo em código.

## Ambiente Dev
O ambiente de dev precisa de algumas dependencias. A maneira mais rápida e fácil de subir todas dependencias é utilizando o `docker compose`. 

### Docker compose
Instale o plugin no seu docker para utilizar o compose. Existe um arquivo docker-compose.yml na raiz do projeto. Execute `docker compose up -d` para subir um ambiente com a api 
rodando e todas suas dependencias. Depois pare de executar a api com `docker compose stop api`. Dessa forma poderá rodar localmente o quarkus e utilizar somente as dependencias 
do compose. Mas caso queira somente subir um backend para testar o frontend localmente, não precisará do stop, poderá utilizar a api do compose. 

A desvantagem de usar a api pelo compose é ter que re-buildar a cada alteração e fazer deploy dela novamente: `docker compose build`, `docker compose up api -d`. 

## Deploying in STG

Mais detalhes de como fazer deploy em STG e PRD podem ser vistos por [aqui](./deploy-README.md).
