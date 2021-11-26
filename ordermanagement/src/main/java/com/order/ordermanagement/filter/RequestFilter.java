package com.order.ordermanagement.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.ordermanagement.service.AuthService;

@Component
public class RequestFilter implements Filter{
	
	@Autowired
	AuthService authService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = req.getHeader("authorization");
		if(authService.validateToken(token)) {
			chain.doFilter(request, response);
		}else {
			res.sendError(401, "Not Authorized");
		}
	}

}
