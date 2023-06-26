FROM openjdk:17-alpine
WORKDIR /opt
ADD target/log-service.jar /opt/app.jar
ENV PORT 9090
EXPOSE 9090
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
