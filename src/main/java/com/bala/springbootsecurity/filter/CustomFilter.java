package com.bala.springbootsecurity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUrl = UrlUtils.buildRequestUrl((HttpServletRequest) request);

		if (authentication != null) {
			log.info("username is null******" + currentUrl);
			log.info("checking Authentication().getPrincipal() for anonymousUser");
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
				((HttpServletResponse) response).sendRedirect("/spring-boot-security/login");

			} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {

				UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				log.info("checking the getUsername : " + userDetails.getUsername());
				chain.doFilter(request, response);
			}
		} else {
			log.info("username is null******" + currentUrl);
			chain.doFilter(request, response);
		}

	}
}