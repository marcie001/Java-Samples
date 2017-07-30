package com.example.springtablesample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.guava.GuavaModule;

@SpringBootApplication
public class SpringTableSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTableSampleApplication.class, args);
	}

	// Guava 対応の Jackson モジュールを Bean として登録する。
	@Bean
	GuavaModule guavaModule() {
		return new GuavaModule();
	}
}
