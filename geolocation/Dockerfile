FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/geolocation.jar
EXPOSE 9084
RUN sh -c 'touch /app/geolocation.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/geolocation.jar" ]
COPY /geolocation-entrypoint.sh /geolocation-entrypoint.sh
RUN chmod +x /geolocation-entrypoint.sh
ENTRYPOINT ["geolocation-entrypoint.sh"]
