FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/users-center.jar
EXPOSE 8082
RUN sh -c 'touch /app/users-center.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/users-center.jar" ]
COPY /users-center-entrypoint.sh /users-center-entrypoint.sh
RUN chmod +x /users-center-entrypoint.sh
ENTRYPOINT ["users-center-entrypoint.sh"]