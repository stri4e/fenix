FROM openjdk:11-jre-slim
COPY build/libs/*.jar /app/server-mock.jar
EXPOSE 2222
ENV JAVA_OPTS="-Xdebug"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/server-mock.jar" ]