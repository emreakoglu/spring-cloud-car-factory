package com.car.factory.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
@SwaggerDefinition(info = @Info(
        description = "Car Factory Auth API reference for developers",
        version = "V1.0",
        title = "Car Factory Auth API",
        contact = @Contact(
           name = "Emre Akoğlu", 
           email = "emre.akoglu@bil.omu.edu.tr"
        ),
        license = @License(
           name = "Apache 2.0", 
           url = "http://www.apache.org/licenses/LICENSE-2.0"
        )
),
consumes = {"application/json", "application/xml"},
produces = {"application/json", "application/xml"},
schemes = {SwaggerDefinition.Scheme.HTTP})
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Bean
	public Docket postsApi(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.car.factory.auth.controller"))
				.paths(regex("/application.*")).build()
				.pathProvider(new RelativePathProvider(servletContext) {
					@Override
					public String getApplicationBasePath() {
						return "/car-factory-auth" + super.getApplicationBasePath();
					}
				});
	}

//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Car Factory Auth API")
//				.description("Car Factory Auth API reference for developers").contact("carfactoryauth@gmail.com")
//				.license("Car Factory Auth License").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//				.version("1.0").build();
//	}
}
