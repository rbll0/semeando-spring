# Etapa base: Ambiente de build
FROM maven:3.9.4-eclipse-temurin-17 AS base

# Define o diretório de trabalho
WORKDIR /app

# Copia o código-fonte para o container
COPY . .

# Compila o projeto sem rodar os testes
RUN mvn clean install -DskipTests

# Etapa final: Imagem otimizada com JRE
FROM eclipse-temurin:17-jre

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR gerado na etapa anterior
COPY --from=base /app/target/spring-semeando-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
