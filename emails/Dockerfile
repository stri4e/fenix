FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/emails.jar
EXPOSE 8689
EXPOSE 587/tcp
ENV JAVA_OPTS="-Xdebug"
RUN sh -c 'touch /app/emails.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/emails.jar"]
COPY /emails-entrypoint.sh /emails-entrypoint.sh
RUN chmod +x /emails-entrypoint.sh
ENTRYPOINT ["emails-entrypoint.sh"]