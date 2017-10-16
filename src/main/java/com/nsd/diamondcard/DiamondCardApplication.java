package com.nsd.diamondcard;



import com.nsd.diamondcard.Config.SecurityFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ComponentScan
@EnableAutoConfiguration
public class DiamondCardApplication {

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new SecurityFilterConfig();
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DiamondCardApplication.class);
		application.run(args);

	}
}
