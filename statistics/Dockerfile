FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/statistics.jar
EXPOSE 8089
RUN sh -c 'touch /app/statistics.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/statistics.jar" ]
COPY /statistics-entrypoint.sh /statistics-entrypoint.sh
RUN chmod +x /statistics-entrypoint.sh
ENTRYPOINT ["statistics-entrypoint.sh"]