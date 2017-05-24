package demo.spring_reactive.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API to handle the welcome page
 * 
 * @author Pranav 
 * 24-May-2017
 *
 */
@Controller
public class IndexController {
	
	/**
	 * Return welcome page
	 * @return
	 */
	@RequestMapping(value="/")
	public String welcome(){
		return "index";
	}
}
