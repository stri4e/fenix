package com.github.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Primary
@EnableOpenApi
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Value(value = "${swagger.enabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(this.swaggerEnabled).select()
                .apis(RequestHandlerSelectors.basePackage("com.github.gateway"))
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Routing Gateway(Google Cloud Gateway): utilize swagger2 polymerization API File")
                .description("Gateway microservices manager.")
                .version("1.0.0")
                .build();
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("gateway","/v2/api-docs","1.0"));
        resources.add(swaggerResource("admins","/admins/v2/api-docs","1.0"));
        resources.add(swaggerResource("users-center","/users/v2/api-docs","1.0"));
        resources.add(swaggerResource("products","/products/v2/api-docs","1.0"));
        resources.add(swaggerResource("orders","/orders/v2/api-docs","1.0"));
        resources.add(swaggerResource("emails","/emails/v2/api-docs","1.0"));
        resources.add(swaggerResource("statistics","/statistics/v2/api-docs","1.0"));
        resources.add(swaggerResource("websocket","/websocket/v2/api-docs","1.0"));
        resources.add(swaggerResource("managers","/managers/v2/api-docs","1.0"));
        resources.add(swaggerResource("payments","/payments/v2/api-docs","1.0"));
        resources.add(swaggerResource("accounts","/accounts/v2/api-docs","1.0"));
        resources.add(swaggerResource("geolocation","/geolocation/v2/api-docs","1.0"));
        resources.add(swaggerResource("customers","/customers/v2/api-docs","1.0"));
        resources.add(swaggerResource("deliveries","/deliveries/v2/api-docs","1.0"));
        resources.add(swaggerResource("bitcoin","/bitcoin/v2/api-docs","1.0"));
        resources.add(swaggerResource("ethereum","/ethereum/v2/api-docs","1.0"));
        resources.add(swaggerResource("master-card","/master-card/v2/api-docs","1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name,String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
