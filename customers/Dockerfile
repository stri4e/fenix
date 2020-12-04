FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/customers.jar
EXPOSE 8092
RUN sh -c 'touch /app/customers.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/customers.jar" ]
COPY /customers-entrypoint.sh /customers-entrypoint.sh
RUN chmod +x /customers-entrypoint.sh
ENTRYPOINT ["customers-entrypoint.sh"]
