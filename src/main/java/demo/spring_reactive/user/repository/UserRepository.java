package demo.spring_reactive.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import demo.spring_reactive.user.document.User;
import reactor.core.publisher.Mono;


/**
 * @author Pranav 
 * 23-May-2017
 *
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String>, UserCustomRepository{
	
	/**
	 * Get User by Email Id
	 * @param emailId
	 * @return
	 */
	Mono<User> findByEmailId(String emailId);
	
}
