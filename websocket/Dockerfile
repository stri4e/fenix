FROM openjdk:11-jre-slim
COPY build/libs/*.jar /app/discovery.jar
EXPOSE 8187
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8187,suspend=n"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=default -jar /app/discovery.jar" ]