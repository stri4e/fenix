FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/master-card.jar
EXPOSE 8097
RUN sh -c 'touch /app/master-card.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/master-card.jar" ]
COPY /master-card-entrypoint.sh /master-card-entrypoint.sh
RUN chmod +x /master-card-entrypoint.sh
ENTRYPOINT ["master-card-entrypoint.sh"]
