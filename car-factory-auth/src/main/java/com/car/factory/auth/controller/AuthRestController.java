package com.car.factory.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.factory.auth.model.JwtToken;
import com.car.factory.security.AuthRequest;
import com.car.factory.security.CustomUserDetailsService;
import com.car.factory.security.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Auth Token Api Document")
public class AuthRestController {
	
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping(value="/getToken",produces = "application/json")
    @ApiOperation(value = "Get Token Operation")
    public JwtToken creteToken(@RequestBody @ApiParam("Get Token Operation Parameter") AuthRequest authRequest) throws Exception {
        try {
        	// bu satır login ediyor, catch düştüğü durumlarda zaten hata almış oluyor.
        	daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        
        JwtToken token = new JwtToken();
        token.setToken(jwt);
        token.setTokenExpireDate(jwtUtil.extractExpiration(jwt));
        
        return token;
    }

}
