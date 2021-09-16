package com.car.factory.carfactory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{

	@Bean
	public Docket productApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.host("localhost:8085")
				.pathMapping("car-factory")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.car.factory")).paths(regex("/.*")).build()
				.apiInfo(metadata());
		
	}
	
	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Car Factory Boot Swagger").description("\"Car Factory Spring Boot Uygulaması\"")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://wwww.apache.org/license/LICENSE-2.0")
				.contact(new Contact("Emre Akoğlu","github.com/emreakoglu","tr.emreakoglu@gmail.com"))
				.build();
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
