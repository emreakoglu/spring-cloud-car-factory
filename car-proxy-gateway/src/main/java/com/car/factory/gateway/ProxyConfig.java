package com.car.factory.gateway;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@EnableDiscoveryClient
public class ProxyConfig {
	
	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("car_factory_route",
						route -> route.path("/car-factory/**")
								.filters(filter -> filter.stripPrefix(1)) // birinci path'i kaldırır
								.uri("lb://car-factory"))
				
				.route("car_engine_route",
						route -> route.path("/car-engine/**")
								.and()
								.method(HttpMethod.GET,HttpMethod.POST)
								.filters(filter -> filter.stripPrefix(1))
								.uri("lb://car-engine"))
				
				.route("car_hood_route",
						route -> route.path("/car-hood/**")
								.uri("lb://car-hood"))
				.build();
	}

}
