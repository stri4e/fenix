Eureka server url: http://localhost:3000/

Swagger http://localhost:8081/swagger-ui.html#/

Turbine: http://localhost:8281/hystrix

Add Zipkin: 
    - docker run -d -p 9411:9411 openzipkin/zipkin

Zipkin logs url: http://localhost:9411/zipkin/

Grafana: http://localhost:3001

Grafana in docker: docker run -d --name=grafana -p 3000:3000 grafana/grafana 

Prometheus in docker: 
    - docker pull prom/prometheus
    - docker run -d --name=prometheus -p 9090:9090 -v <PATH_TO_prometheus.yml_FILE>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
Prometheus url: http://localhost:9090/