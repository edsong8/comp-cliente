package com.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@OpenAPIDefinition(info = @Info(title = "Cliente Service API", version = "v1", description = "Documentation of Cliente Service API"))
@Configuration
public class OpenApiConfiguration {

    @Value("${spring.application.version}")
    private String version;
    
    private static String title="TWA Sistemas - Cliente API";

    @Bean
    public OpenAPI customOpenAPI() {
	return new OpenAPI().components(new Components())
		.info(new io.swagger.v3.oas.models.info.Info().title(title).version(version));
    }

    private ApiInfo apiInfo() {
	return new ApiInfoBuilder().title(title).description("End-point's para Cliente").version(version)
		.contact(new Contact("Suporte", "Suporte", "twa@twas.com.br")).build();
    }

    @Bean
    public Docket demoApi() {
	return new Docket(DocumentationType.OAS_30).select()
		.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
		.build().apiInfo(apiInfo());
    }

}