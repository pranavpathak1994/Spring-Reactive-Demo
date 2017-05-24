package demo.spring_reactive.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Rest API for the user modules
 * 
 * @author Pranav 
 * 23-May-2017
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	final private UserRepository userRepository;
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	/**
	 * Get User by userId
	 * @param userId
	 * @return
	 */
	@GetMapping("/userId/{userId}")
	public Mono<ResponseEntity<Response>> findByUserId(@PathVariable("userId") String userId) {
		logger.info("Get user by User id --> "+userId);
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
	
	/**
	 * Get user by its emailId
	 * @param emailId
	 * @return
	 */
	@GetMapping("/emailId/{emailId}")
	public Mono<ResponseEntity<Response>> findByEmailId(@PathVariable("emailId") String emailId) {
		Response response= new Response();
		response.setMessage(ResponseUtil.DATA_GET_SUCCESS);
		logger.info("Get user by email id --> "+emailId);
		return userRepository.findByEmailId(emailId)
				.map((user) ->{
					Map<String, Object> map = new HashMap<>();
					map.put(MessageUtils.USER_KEY, user);
					response.setObject(map);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				});
	}
	/**
	 * Get all users
	 * @return
	 */
	@GetMapping()
	public Mono<ResponseEntity<Response>> findAll() {
		Response response= new Response();
		response.setMessage(ResponseUtil.DATA_GET_SUCCESS);
		logger.info("Get all users");
		Map<String, Object> map = new HashMap<>();
		return userRepository.findAll().collectList().map((users)->{
			map.put(MessageUtils.USER_KEY, users);
			response.setObject(map);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
			
		});
	}
	
	/**
	 * Save user. If user is not present then user will create and if its present then it will update the user
	 * @param user
	 * @return
	 */
	@PostMapping
	public Mono<ResponseEntity<Response>> saveUser(@RequestBody User user) {
		Response response= new Response();
		ResponseEntity<Response> responseEntity;
		User emailUser=userRepository.findByEmailId(user.getEmailId()).block();
		
		if(user.getUserId()==null){
			logger.info("Trying to create user with email id -> "+ user.getEmailId());
			if(emailUser==null){
				user.setCreatedDate(new Date());
				userRepository.save(user).subscribe().doOnSuccess((success) ->{
					logger.info("User created with email id ->" +user.getEmailId());
				});
				
				response.setMessage(ResponseUtil.USER_CREATED_SUCCESS);
				responseEntity = new ResponseEntity<Response>(response, HttpStatus.CREATED);
			}
			else{
				logger.info("User conflict with email id ->" +user.getEmailId());
				response.setMessage(ResponseUtil.USER_EXIST);
				responseEntity = new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
			}
		}
		else{
			boolean isExist=false;
			
			logger.info("Trying to update user with email id -> "+ user.getEmailId() +" and user id -> "+user.getUserId());
			
			if(emailUser!=null && !user.getUserId().equals(emailUser.getUserId()))
				isExist=true;
			
			if(!isExist){
				User oldUser=userRepository.findByUserId(user.getUserId()).block();
				user.setCreatedDate(oldUser.getCreatedDate());
				userRepository.save(user).subscribe().doOnSubscribe((success)->{
					logger.info("User updated with email id ->" +user.getEmailId());
				});
				response.setMessage(ResponseUtil.USER_UPDATED_SUCCESS);
				responseEntity = new ResponseEntity<Response>(response, HttpStatus.OK);
			}else{
				logger.info("User conflict with email id ->" +user.getEmailId());
				response.setMessage(ResponseUtil.USER_EXIST);
				responseEntity = new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
			}
		}
				

		return Mono.just(responseEntity);
	}
	
	
	
}
