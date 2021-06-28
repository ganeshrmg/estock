package com.estock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/loginpage").setViewName("loginpage");
	} */

@Override
public void addCorsMappings(CorsRegistry corsRegistry) {
    corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("*")
            .maxAge(3600L)
            .allowedHeaders("*")
            .exposedHeaders("Authorization")
            .allowCredentials(true);
}
	
} 