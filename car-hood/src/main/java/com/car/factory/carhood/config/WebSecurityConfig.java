package com.car.factory.carhood.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.car.factory.security.JwtFilter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtFilter jwtFilter;
	
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
        http.csrf().disable()
		        .logout().disable()
		        .formLogin().disable()
                .authorizeRequests()
                	.antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/swagger**","/v2/**").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers("/application/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    
    }
}
