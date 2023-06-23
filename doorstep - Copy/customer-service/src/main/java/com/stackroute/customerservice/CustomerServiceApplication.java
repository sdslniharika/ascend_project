package com.stackroute.customerservice;

import com.stackroute.customerservice.constants.CustomerConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@OpenAPIDefinition(servers = {@Server(url = "${open.ui:/}", description = "Default Server URL")})
@EnableDiscoveryClient
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes(CustomerConstants.HEADER_AUTHORIZATION, new SecurityScheme()
								.type(SecurityScheme.Type.APIKEY)
								.in(SecurityScheme.In.HEADER)
								.name(CustomerConstants.HEADER_AUTHORIZATION)))
				.addSecurityItem(new SecurityRequirement().addList(CustomerConstants.HEADER_AUTHORIZATION));
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate()
	{
		RestTemplate template=new RestTemplate();
		return  template;
	}

}
