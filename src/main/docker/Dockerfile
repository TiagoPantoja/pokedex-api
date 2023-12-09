# Imagem base GraalVM
FROM ghcr.io/graalvm/graalvm-ce:java11-22.0.0.2 AS build

WORKDIR /app

COPY . .

# Buildar o projeto
RUN ./mvnw clean package -Pnative

# Produção
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.5

WORKDIR /app

COPY --from=build /app/target/*-runner /app/app

EXPOSE 8080

CMD ["./app", "-Dquarkus.http.host=0.0.0.0"]