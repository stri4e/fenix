FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/admins.jar
EXPOSE 8083
RUN sh -c 'touch /app/admins.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/admins.jar" ]
COPY /admins-entrypoint.sh /admins-entrypoint.sh
RUN chmod +x /admins-entrypoint.sh
ENTRYPOINT ["admins-entrypoint.sh"]