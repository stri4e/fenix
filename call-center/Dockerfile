FROM golang
RUN apt-get update
RUN apt install -y netcat
WORKDIR /
COPY build /
EXPOSE 8987
ENTRYPOINT ["./call-center"]
COPY /call-center-entrypoint.sh /call-center-entrypoint.sh
RUN chmod +x /call-center-entrypoint.sh
ENTRYPOINT ["call-center-entrypoint.sh"]