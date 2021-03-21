package com.car.factory.auth.config;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import shuaicj.example.security.common.JwtAuthenticationConfig;
import shuaicj.example.security.common.JwtTokenAuthenticationFilter;
import shuaicj.example.security.common.JwtUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired 
	JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
    	
        return new JwtAuthenticationConfig();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER").and()
//                .withUser("shuaicj").password(encoder.encode("shuaicj")).roles("USER");
//    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	//config.setExpiration(60*1);
    	httpSecurity.headers().frameOptions().disable();
    	httpSecurity  .csrf().disable()
        .logout().disable()
        .formLogin().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .anonymous()
        .and()
            .exceptionHandling().authenticationEntryPoint(
                    (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
            .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()), // bu servisin token üretmesi için gerekli
                    UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new JwtTokenAuthenticationFilter(config), UsernamePasswordAuthenticationFilter.class) // bu servisin token ile kullanılması için gerekli
        .authorizeRequests()
        .antMatchers(config.getUrl()).permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/swagger**","/v2/**").permitAll()
        .antMatchers("/application/**").hasRole("USER")
        .anyRequest().authenticated();
    	
    	
    	
    	
//        httpSecurity
//                .csrf().disable()
//                .logout().disable()
//                .formLogin().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                    .anonymous()
//                .and()
//                    .exceptionHandling().authenticationEntryPoint(
//                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                    .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()),
//                            UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                    .antMatchers(config.getUrl()).permitAll()
//                    .anyRequest().authenticated();
    }
}
