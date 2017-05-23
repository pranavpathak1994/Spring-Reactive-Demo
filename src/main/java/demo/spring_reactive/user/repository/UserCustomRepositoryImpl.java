package demo.spring_reactive.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import demo.spring_reactive.user.document.User;
import reactor.core.publisher.Mono;

/**
 * @author Pranav 
 * 23-May-2017
 *
 */
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository{
	
	@Autowired
	private ReactiveMongoTemplate template;

	/**
	 * Find user by his user id
	 */
	public Mono<User> findByUserId(String userId) {
		
		return template.findById(userId, User.class);
	}

}
