FROM java:8

WORKDIR /

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

CMD ["java", "-jar", "-Dspring.datasource.url=jdbc:mysql://backpackerdb:3306/buses", "app.jar"]