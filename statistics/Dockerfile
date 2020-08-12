FROM golang
RUN apt-get update
RUN apt install -y netcat
WORKDIR /
COPY build /
EXPOSE 8787
ENTRYPOINT ["./statistics"]
COPY /statistics-entrypoint.sh /statistics-entrypoint.sh
RUN chmod +x /statistics-entrypoint.sh
ENTRYPOINT ["statistics-entrypoint.sh"]