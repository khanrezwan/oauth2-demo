package com.rezwanislam.oauth2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class Oauth2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2DemoApplication.class, args);
	}
}

//todo following these tuts
// https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html
// https://github.com/dsyer/sparklr-boot
// https://jugbd.org/2017/09/19/implementing-oauth2-spring-boot-spring-security/
// https://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/package-summary.html