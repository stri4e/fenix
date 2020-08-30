FROM openjdk:11-jre-slim
COPY build/libs/*.jar /app/discovery.jar
EXPOSE 3000
RUN sh -c 'touch /app/discovery.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/discovery.jar" ]