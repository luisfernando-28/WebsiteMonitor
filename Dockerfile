# Etapa 1: build do projeto (gera o .jar)
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia o pom e baixa as dependências (cache melhor)
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copia o código fonte
COPY src ./src

# Build do jar (sem rodar testes)
RUN mvn -B clean package -DskipTests

# Etapa 2: imagem final, só com o jar
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o jar gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Porta que o Spring Boot usa
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]