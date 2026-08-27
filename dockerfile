# ========== ETAPA 1: COMPILAR O PROJETO ==========
FROM maven:3.9-eclipse-temurin:17 AS builder
WORKDIR /app

# Copia apenas o pom.xml primeiro → aproveita cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante e compila (sem rodar testes)
COPY src ./src
RUN mvn clean package -DskipTests

# ========== ETAPA 2: IMAGEM FINAL ==========
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o .jar compilado
COPY --from=builder /app/target/*.jar app.jar

# ⚠️ IMPORTANTE: Sua porta é 8089 (não 8080!)
EXPOSE 8089

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]