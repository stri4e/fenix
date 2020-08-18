FROM golang
RUN apt-get update
RUN apt install -y netcat
WORKDIR /
COPY build /
EXPOSE 8887
ENTRYPOINT ["./managers"]
COPY /managers-entrypoint.sh /managers-entrypoint.sh
RUN chmod +x /managers-entrypoint.sh
ENTRYPOINT ["managers-entrypoint.sh"]