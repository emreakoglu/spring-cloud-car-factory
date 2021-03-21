package com.car.factory.carfactoryemployees.config;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SwaggerDefinition(info = @Info(
        description = "Car Factory Employee API reference for developers",
        version = "V1.0",
        title = "Car Factory Employee API",
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
public class SwaggerConfiig extends WebMvcConfigurerAdapter {
	
	@Bean
	public Docket postsApi(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.car.factory.carfactoryemployees.controller"))
				.paths(regex("/application.*")).build()
				.pathProvider(new RelativePathProvider(servletContext) {
					@Override
					public String getApplicationBasePath() {
						return "/car-factory-employees" + super.getApplicationBasePath();
					}
				});
	}


}
