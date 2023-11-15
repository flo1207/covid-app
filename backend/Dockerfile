FROM eclipse-temurin:17-jdk-jammy AS builder-backend

WORKDIR /backend
COPY /backend/ .

RUN ./gradlew build

FROM eclipse/ubuntu_jre

WORKDIR /backend
COPY --from=builder-backend /backend/build/libs .

CMD ["java","-jar", "/backend/covid-api-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
