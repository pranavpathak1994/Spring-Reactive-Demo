package demo.spring_reactive.user.repository;

import demo.spring_reactive.user.document.User;
import reactor.core.publisher.Mono;

/**
 * @author Pranav 
 * 23-May-2017
 *
 */
public interface UserCustomRepository {
	
	/**
	 * Find user by his user id
	 */
	public Mono<User> findByUserId(String userId);
}
