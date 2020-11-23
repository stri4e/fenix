FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/deliveries.jar
EXPOSE 9083
RUN sh -c 'touch /app/deliveries.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/deliveries.jar" ]
COPY /deliveries-entrypoint.sh /deliveries-entrypoint.sh
RUN chmod +x /deliveries-entrypoint.sh
ENTRYPOINT ["deliveries-entrypoint.sh"]
