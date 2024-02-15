package com.aashdit.lms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "com.aashdit.lms")
@ComponentScan(basePackages = {"com.aashdit.lms.*","com.aashdit.umt.*","com.aashdit.demography.*",  "com.aashdit.beneficiary.*","com.aashdit.fund.*", "com.aashdit.lms.utils"})
@EnableJpaRepositories(basePackages = {"com.aashdit.umt.repository","com.aashdit.demography.repository","com.aashdit.lms.repository", "com.aashdit.beneficiary.repository","com.aashdit.fund.repository", "com.aashdit.budget.repository"}) 
@EntityScan(basePackages = {"com.aashdit.umt.model","com.aashdit.lms.model","com.aashdit.demography.dto","com.aashdit.demography.model", "com.aashdit.beneficiary.model","com.aashdit.fund.model", "com.aashdit.budget.model"}) 
@EnableScheduling
@EnableAutoConfiguration
public class LmsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LmsApplication.class);
	}
}
	