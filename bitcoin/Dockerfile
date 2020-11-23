FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/bitcoin.jar
EXPOSE 8095
RUN sh -c 'touch /app/bitcoin.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/bitcoin.jar" ]
COPY /bitcoin-entrypoint.sh /bitcoin-entrypoint.sh
RUN chmod +x /bitcoin-entrypoint.sh
ENTRYPOINT ["bitcoin-entrypoint.sh"]
