FROM eclipse-temurin:17-jdk-jammy


WORKDIR /backend
COPY . .

RUN ./gradlew build
CMD ["java","-jar", "/backend/build/libs/covid-api-0.0.1-SNAPSHOT.jar"]

