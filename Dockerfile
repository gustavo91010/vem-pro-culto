FROM gradle:8.7-jdk17 AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew build -x test

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=build /app/build/libs/vem-pro-culto-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
# FROM gradle:8.7-jdk17 AS build
# WORKDIR /app
# COPY . .
# RUN gradle build -x test
# 
# FROM eclipse-temurin:17-jdk-jammy
# WORKDIR /app
# # Copiando apenas o JAR executável
# COPY --from=build /app/build/libs/vem-pro-culto-api-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","app.jar"]
