package demo.spring_reactive.util.response;

import java.util.Map;

public class Response {
	
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
