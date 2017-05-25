package demo.spring_reactive.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring_reactive.user.repository.UserTrashRepository;
import demo.spring_reactive.util.response.MessageUtils;
import demo.spring_reactive.util.response.Response;
import demo.spring_reactive.util.response.ResponseUtil;
import reactor.core.publisher.Mono;

/**
 * @author Pranav 
 * 25-May-2017
 *
 */
@RestController
@RequestMapping("/trashUsers")
public class UserTrashController {
	
	final private UserTrashRepository userTrashRepository;
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	public UserTrashController(UserTrashRepository userTrashRepository) {
		this.userTrashRepository=userTrashRepository;
	}
	
	/**
	 * Get all trash user
	 * @return
	 */
	@GetMapping()
	public Mono<ResponseEntity<Response>> findAll() {
		Response response= new Response();
		response.setMessage(ResponseUtil.DATA_GET_SUCCESS);
		logger.info("Get all users");
		Map<String, Object> map = new HashMap<>();
		return userTrashRepository.findAll().collectList().map((users)->{
			map.put(MessageUtils.USER_TRASH_KEY, users);
			response.setObject(map);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
			
		});
	}
}
