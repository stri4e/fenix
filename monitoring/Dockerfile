FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/monitoring.jar
EXPOSE 8281
ENV JAVA_OPTS="-Xdebug"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/monitoring.jar" ]
COPY /monitoring-entrypoint.sh /monitoring-entrypoint.sh
RUN chmod +x /monitoring-entrypoint.sh
ENTRYPOINT ["monitoring-entrypoint.sh"]