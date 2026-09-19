# ========== ETAPA 1: COMPILAR O PROJETO ==========
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ========== ETAPA 2: IMAGEM FINAL ==========
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8089

# ADICIONADO: -Xms128m -Xmx350m para a JVM não estourar a RAM do servidor
ENTRYPOINT ["java", "-Xms128m", "-Xmx350m", "-jar", "app.jar"]