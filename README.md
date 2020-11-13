Eureka server url: 

        http://localhost:3000/

Swagger: 

        http://localhost:8081/swagger-ui/index.html
        
        http://127.0.0.1:8081/products/swagger-ui.html#/

Turbine: 
        
        http://localhost:8281/hystrix

Add Zipkin: 

    - docker run -d -p 9411:9411 openzipkin/zipkin

Zipkin logs url:
        
        http://localhost:9411/zipkin/

Grafana: 
        
        http://localhost:3001

Grafana in docker:
        
        docker run -d --name=grafana -p 3000:3000 grafana/grafana 

Prometheus in docker: 
        
        docker pull prom/prometheus
        docker run -d --name=prometheus -p 9090:9090 -v <PATH_TO_prometheus.yml_FILE>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml

Prometheus url:

        http://localhost:9090/

Docker composer useful command:

        docker-compose -f docker-compose.default.yml down --rmi all
        docker-compose -f docker-compose.default.yml up
        docker-compose -f docker-compose.default.yml ps

Start docker-compose with default or test profile:

        default:
        docker-compose -f docker-compose.default.yml up -d
        docker-compose -f docker-compose.default.yml up -d server-mocks
        docker-compose -f docker-compose.default.yml up -d postgresdb

        test:
        docker-compose -f docker-compose.test.yml up -d
        docker-compose -f docker-compose.test.yml up -d server-mocks
        docker-compose -f docker-compose.test.yml up -d postgresdb

Stop docker-compose with profile default or test:  

        default:
        docker-compose -f docker-compose.default.yml down --rmi all
        test:
        docker-compose -f docker-compose.test.yml down --rmi all     

Plugin for parsing json to sh script:
        
        sudo apt install jq

Start only one service use docker-compose:
        
        ./run.sh start docker-compose.dev.yml admins

Stop only one service use docker-compose:

        ./run.sh stop docker-compose.dev.yml admins

Start all services use docker-composer: 

        ./run.sh start_all docker-compose.dev.yml

Stop all services use docker-composer:

        ./run.sh stop_all docker-compose.dev.yml

Meest:
    
    https://wiki.meest-group.com/api/ua/v3.0/openAPI#/

Novaposhta:

    https://devcenter.novaposhta.ua/docs/services/
    
