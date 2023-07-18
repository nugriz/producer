package com.microservice.producer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.microservice" })
@RestController
@EnableConfigurationProperties
@EnableDiscoveryClient
public class ProducerApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Value("${eureka.client.enabled}")
	private boolean enableEureka = true;
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		builder.setReadTimeout(Duration.ofMinutes(1));
		builder.setConnectTimeout(Duration.ofMinutes(1));
		
		return builder.build();
	}
	
	@Bean
	public Docket api() {
		Contact contact = new Contact("name", "http://tritronik.com", "risna.hendayana@tritronik.com");
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		if(enableEureka) {
			logger.info("Eureka is enable, (deployment mode) add Authorization as param at Header");
			addAuthDocket(docket);
		}else {
			logger.info("Eureka is disabled, (development mode), no need to add Authorization as param at Header");
		}
		
		return docket;
	}
	
	public void run(String... args) throws Exception {
		logger.info("Running Smartsol Micro Service ... ");
	}
	

	public void addAuthDocket(Docket docket) {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();

		Parameter authHeaderParameter = aParameterBuilder
				.name("authorization") 
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.defaultValue("")
				.required(true) 
				.build();

		List<Parameter> params = new ArrayList<Parameter>();
		params.add(authHeaderParameter);
		
		docket.globalOperationParameters(params);
	}
	
	
	@GetMapping("/")
	public String welcome() {
		return "welcome at Smartsol Micro Sample";
	}
//	@Bean
//	  public WebMvcConfigurer corsConfigurer() {
//	    return new WebMvcConfigurerAdapter() {
//	      @Override
//	      public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**").allowedOrigins("*");
//	      }
//	    };
//	  }
}
