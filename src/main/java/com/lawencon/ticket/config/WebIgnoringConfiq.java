package com.lawencon.ticket.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class WebIgnoringConfiq {
	private List<RequestMatcher> matchers = new ArrayList<>();

	@Bean
	public List<RequestMatcher> antMatchers() {
		matchers.add(new AntPathRequestMatcher("/login", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/v3/api-docs/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));
		return matchers;
	}
}
