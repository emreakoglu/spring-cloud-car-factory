package com.car.factory.zuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class CarZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarZuulGatewayApplication.class, args);
	}
	
//	@Bean
//    public PreFilter preFilter() {
//        return new PreFilter();
//    }
//    @Bean
//    public PostFilter postFilter() {
//        return new PostFilter();
//    }
//    @Bean
//    public ErrorFilter errorFilter() {
//        return new ErrorFilter();
//    }
//    @Bean
//    public RouteFilter routeFilter() {
//        return new RouteFilter();
//    }

}
