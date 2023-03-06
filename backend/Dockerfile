FROM openjdk:11

ENV DATASOURCE_URL=jdbc:postgresql://database:5432/open_certificate
ENV QUARKUS_DATASOURCE_USERNAME=postgres
ENV QUARKUS_DATASOURCE_PASSWORD=postgres
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

WORKDIR /api
EXPOSE 8080 8080
COPY target/quarkus-app/ .
COPY target/quarkus-app/quarkus-run.jar api.jar

#run ls

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTIONS -jar api.jar"]
