package com.car.factory.carfactory.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HeaderInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		// TODO Auto-generated method stub
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession httpSession = attr.getRequest().getSession(true);
	    String authorization = (String) httpSession.getAttribute("Authorization");
       
        
        request.getHeaders().add("Authorization", authorization);
        return execution.execute(request, body);
	}

}
