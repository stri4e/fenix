FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/gateway.jar
EXPOSE 8081
RUN sh -c 'touch /app/gateway.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/gateway.jar" ]
COPY /gateway-entrypoint.sh /gateway-entrypoint.sh
RUN chmod +x /gateway-entrypoint.sh
ENTRYPOINT ["gateway-entrypoint.sh"]