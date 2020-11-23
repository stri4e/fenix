FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/websocket.jar
EXPOSE 8094
RUN sh -c 'touch /app/websocket.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/websocket.jar" ]
COPY /websocket-entrypoint.sh /websocket-entrypoint.sh
RUN chmod +x /websocket-entrypoint.sh
ENTRYPOINT ["websocket-entrypoint.sh"]