package com.car.factory.carfactoryemployees.config;

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

import shuaicj.example.security.common.JwtAuthenticationConfig;
import shuaicj.example.security.common.JwtTokenAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationConfig config;

	@Bean
	public JwtAuthenticationConfig jwtConfig() {
		return new JwtAuthenticationConfig();
	}
	
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
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .anonymous()
        .and()
            .exceptionHandling().authenticationEntryPoint(
                    (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
            .addFilterAfter(new JwtTokenAuthenticationFilter(config), UsernamePasswordAuthenticationFilter.class) // bu servisin token ile kullanılması için gerekli
        .authorizeRequests()
        .antMatchers(config.getUrl()).permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/swagger**","/v2/**").permitAll()
        .antMatchers("/application/**").hasRole("USER")
        .anyRequest().authenticated();
    	
    }
}
