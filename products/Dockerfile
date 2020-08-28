FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt install -y netcat
COPY build/libs/*.jar /app/products.jar
EXPOSE 8686
ENV JAVA_OPTS="-Xdebug"
RUN sh -c 'touch /app/products.jar'
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/products.jar" ]
COPY /products-entrypoint.sh /products-entrypoint.sh
RUN chmod +x /products-entrypoint.sh
ENTRYPOINT ["products-entrypoint.sh"]