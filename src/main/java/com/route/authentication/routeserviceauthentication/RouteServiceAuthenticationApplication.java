package com.route.authentication.routeserviceauthentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class RouteServiceAuthenticationApplication {
	final static Logger logger = LoggerFactory.getLogger(RouteServiceAuthenticationApplication.class);
	public static void main(String[] args) throws Exception {
			SpringApplication.run(RouteServiceAuthenticationApplication.class, args);
			logger.info("--Application Started--");
	}
}
