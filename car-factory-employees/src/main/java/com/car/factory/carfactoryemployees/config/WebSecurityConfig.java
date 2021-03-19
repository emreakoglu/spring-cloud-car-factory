package com.car.factory.carfactoryemployees.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .withUser("emre").password("emre")
        .authorities("ROLE_ADMIN","ROLE_USER");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
        http
            .csrf()
                .disable()
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/application/**").hasAnyRole("ADMIN","USER")
            .anyRequest().authenticated();
    }
}
