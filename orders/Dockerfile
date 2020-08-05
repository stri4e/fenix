FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/orders.jar
EXPOSE 8687
ENV JAVA_OPTS="-Xdebug"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/orders.jar" ]
COPY /orders-entrypoint.sh /orders-entrypoint.sh
RUN chmod +x /orders-entrypoint.sh
ENTRYPOINT ["orders-entrypoint.sh"]