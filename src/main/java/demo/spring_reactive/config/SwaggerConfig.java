package demo.spring_reactive.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration
 * 
 * @author Pranav 
 * 23-May-2017
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Configure Swagger for the application.
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("demo"))
				.paths(PathSelectors.ant("/*")).build().apiInfo(apiInfo());
	}
	
	/**
	 * Provide the api info for the swagger
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Demo of Spring 5", "1.0",
				"Terms of service", new Contact("Pranav Pathak", "www.pranavpathak.in", "pranavpathak29@gmail.com"), 
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
		

		
	}
}
