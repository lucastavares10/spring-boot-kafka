FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src ./src

EXPOSE 5002

CMD ["./mvnw", "spring-boot:run"]
