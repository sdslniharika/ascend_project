package com.stackroute.feedbackservice;

import com.stackroute.feedbackservice.constants.FeedbackConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(servers = {@Server(url = "${open.ui:/}", description = "Default Server URL")})
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FeedbackServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedbackServiceApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(FeedbackConstants.HEADER_AUTHORIZATION, new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name(FeedbackConstants.HEADER_AUTHORIZATION)))
                .addSecurityItem(new SecurityRequirement().addList(FeedbackConstants.HEADER_AUTHORIZATION));
    }
}
