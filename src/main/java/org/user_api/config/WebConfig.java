package org.user_api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//spring.datasource.url=jdbc:postgresql:
//localhost:5432/user_api
//spring.datasource.username=postgres
//spring.datasource.password=lahcen2025
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.user_api.controller","org.user_api.exception"})
public class WebConfig {

}
