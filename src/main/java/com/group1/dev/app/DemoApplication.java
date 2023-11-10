package com.group1.dev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class DemoApplication {
	
	@Bean
	public ApplicationRunner applicationStartupRunner() {
        return new ApplicationRunner();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
