FROM adoptopenjdk:11-jre-hotspot
VOLUME /upload
COPY target/*.jar app.jar
CMD ["java","-jar","/app.jar"]
