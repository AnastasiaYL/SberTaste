#образ взятый за основу
FROM openjdk:15
#FROM maven:3.8.7
#Записываем в переменную путь до варника (необязательно)
ARG jarFile=target/SberTaste-1.jar
#Куда переместить варник внутри контейнера
WORKDIR /opt/app
#копируем наш джарник в новый внутри контейнера
#RUN mvn clean install -DskipTests
COPY ${jarFile} SberTaste.jar
#Открываем порт
EXPOSE 8080
#Команда для запуска
ENTRYPOINT ["java", "-jar", "SberTaste.jar"]
