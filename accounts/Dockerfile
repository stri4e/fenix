FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/accounts.jar
EXPOSE 8090
RUN sh -c 'touch /app/accounts.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/accounts.jar" ]
COPY /accounts-entrypoint.sh /accounts-entrypoint.sh
RUN chmod +x /accounts-entrypoint.sh
ENTRYPOINT ["accounts-entrypoint.sh"]
