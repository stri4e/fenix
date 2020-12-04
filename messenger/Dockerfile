FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/messenger.jar
EXPOSE 8097
RUN sh -c 'touch /app/messenger.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/messenger.jar" ]
COPY /messenger-entrypoint.sh /messenger-entrypoint.sh
RUN chmod +x /messenger-entrypoint.sh
ENTRYPOINT ["messenger-entrypoint.sh"]
