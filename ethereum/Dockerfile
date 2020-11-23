FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/ethereum.jar
EXPOSE 8096
RUN sh -c 'touch /app/ethereum.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/ethereum.jar" ]
COPY /ethereum-entrypoint.sh /ethereum-entrypoint.sh
RUN chmod +x /ethereum-entrypoint.sh
ENTRYPOINT ["ethereum-entrypoint.sh"]
