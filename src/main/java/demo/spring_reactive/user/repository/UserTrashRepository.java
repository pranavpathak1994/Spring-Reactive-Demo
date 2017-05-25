package demo.spring_reactive.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import demo.spring_reactive.user.document.UserTrash;


/**
 * @author Pranav 
 * 23-May-2017
 *
 */
@Repository
public interface UserTrashRepository extends ReactiveCrudRepository<UserTrash, String>{
	
}
