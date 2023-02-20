FROM openjdk:15
ARG jarFile=target/SberTaste-1.jar
WORKDIR /opt/app
COPY ${jarFile} SberTaste.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SberTaste.jar"]
