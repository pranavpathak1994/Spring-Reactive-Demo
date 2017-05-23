package demo.spring_reactive.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring_reactive.user.document.User;
import demo.spring_reactive.user.repository.UserRepository;
import demo.spring_reactive.util.response.MessageUtils;
import demo.spring_reactive.util.response.Response;
import demo.spring_reactive.util.response.ResponseUtil;
import reactor.core.publisher.Mono;


/**
 * @author Pranav 
 * 23-May-2017
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/userId/{userId}")
	public Mono<ResponseEntity<Response>> findByUserId(@PathVariable("userId") String userId) {
		Response response= new Response();
		response.setMessage(ResponseUtil.DATA_GET_SUCCESS);
		return userRepository.findByUserId(userId)
				.map((user) ->{
					Map<String, Object> map = new HashMap<>();
					map.put(MessageUtils.USER_KEY, user);
					response.setObject(map);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				});
	}
	
	@GetMapping(value = "/emailId/{emailId}")
	public Mono<ResponseEntity<Response>> findByEmailId(@PathVariable("emailId") String emailId) {
		Response response= new Response();
		response.setMessage(ResponseUtil.DATA_GET_SUCCESS);
		return userRepository.findByEmailId(emailId)
				.map((user) ->{
					Map<String, Object> map = new HashMap<>();
					map.put(MessageUtils.USER_KEY, user);
					response.setObject(map);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				});
	}
	
	@PostMapping
	public Mono<ResponseEntity<Response>> saveUser(@RequestBody User user) {
		Response response= new Response();
		ResponseEntity<Response> responseEntity;
		if(user.getUserId()==null){
			user.setCreatedDate(new Date());
			userRepository.save(user);
			
			response.setMessage(ResponseUtil.USER_CREATED_SUCCESS);
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.CREATED);
		}
		else{
			userRepository.findByUserId(user.getUserId()).doOnSuccess((oldUser) ->{
				user.setCreatedDate(oldUser.getCreatedDate());
				userRepository.save(user);
			});
			
			response.setMessage(ResponseUtil.USER_UPDATED_SUCCESS);
			responseEntity= new ResponseEntity<Response>(response, HttpStatus.OK);
		}
				
		return Mono.just(responseEntity);
		
	}
	
}
