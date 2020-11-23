FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/payments.jar
EXPOSE 9081
RUN sh -c 'touch /app/payments.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/payments.jar" ]
COPY /payments-entrypoint.sh /payments-entrypoint.sh
RUN chmod +x /payments-entrypoint.sh
ENTRYPOINT ["payments-entrypoint.sh"]
