FROM openjdk:8 as build-server
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ["chmod", "+x", "./mvnw"]
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)



FROM openjdk:8-jre-alpine

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build-server ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build-server ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build-server ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.server.DiscoveryServerApplication"]
EXPOSE 8010