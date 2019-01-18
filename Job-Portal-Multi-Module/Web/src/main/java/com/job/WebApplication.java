package com.job;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("com.sun.faces.expressionFactory", "org.apache.el.ExpressionFactoryImpl");
	}
}