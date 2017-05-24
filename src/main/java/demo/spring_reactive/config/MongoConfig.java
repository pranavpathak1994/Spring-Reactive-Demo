package demo.spring_reactive.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

/**
 * Mongo DB configurtaion 
 * 
 * @author Pranav 
 * 23-May-2017
 *
 */
@Configuration
@EnableReactiveMongoRepositories("demo")
public class MongoConfig{
	
	@Value("${mongo.database}")
	private String mongo_database;

	@Value("${mongo.ip}")
	private String mongo_client_ip;

	@Value("${mongo.port}")
	private Integer mongo_port;
	
	/**
	 * Return the MongoDB Instance
	 * @return
	 */
	public @Bean MongoClient mongoClient() {
		List<Object> args = new ArrayList<Object>();
		args.add(mongo_client_ip);
		args.add(mongo_port);
		return MongoClients.create(String.format("mongodb://%s:%d", args.toArray()));
	}
	
	/**
	 * Return a reactive MongoTemplate bean
	 * @return
	 */
	public @Bean ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(mongoClient(), mongo_database);
	}
	
	

}
