package demo.spring_reactive.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Pranav 
 * 23-May-2017
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	/**
	 * To start a spring boot application
	 * @param args
	 */
	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		return application.sources(Application.class);
	}
}
