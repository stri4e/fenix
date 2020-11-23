FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/managers.jar
EXPOSE 8887
RUN sh -c 'touch /app/managers.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/managers.jar" ]
COPY /managers-entrypoint.sh /managers-entrypoint.sh
RUN chmod +x /managers-entrypoint.sh
ENTRYPOINT ["managers-entrypoint.sh"]