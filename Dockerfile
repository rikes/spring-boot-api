FROM openjdk:8

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR app

COPY target/spring-boot-api-0.0.1-SNAPSHOT.jar /app/spring-boot-api.jar

ENTRYPOINT ["java", "-jar", "spring-boot-api.jar"]