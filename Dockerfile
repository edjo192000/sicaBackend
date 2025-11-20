# Etapa de construcción
FROM gradle:8.5-jdk21-alpine AS build
WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle

# Descargar dependencias (se cachea esta capa)
RUN gradle dependencies --no-daemon

# Copiar código fuente
COPY src ./src

# Construir la aplicación
RUN gradle bootJar --no-daemon -x test

# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no-root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Variables de entorno por defecto
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Exponer puerto
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/api/health || exit 1

# Comando de inicio
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
