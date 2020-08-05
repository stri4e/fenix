FROM openjdk:11-jre-slim
COPY build/libs/*.jar /app/config-server.jar
EXPOSE 8888
ENV JAVA_OPTS="-Xdebug"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/config-server.jar" ]