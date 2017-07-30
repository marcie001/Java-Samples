package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@Controller
public class SpringThymeleafValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringThymeleafValidationApplication.class, args);
	}

	/**
	 * Spring Boot のメッセージソースを validation のメッセージソースとして利用する.
	 * 
	 * @param messageSource
	 *            メッセージソース
	 * @return validation のメッセージソース
	 */
	@Autowired
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}
}
