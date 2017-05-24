package demo.spring_reactive.util.response;

import java.io.Serializable;
import java.util.Map;

/**
 * Mapper for the response
 * 
 * @author Pranav 
 * 24-May-2017
 *
 */
public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	private Map<String, Object> object;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message; 
	}

	public Map<String, Object> getObject() {
		return object;
	}

	public void setObject(Map<String, Object> object) {
		this.object = object;
	}
	
	public Response() {

	}
	
	public Response(String message, Map<String, Object> object) {
		super();
		this.message = message;
		this.object = object;
	}
	
	
}
