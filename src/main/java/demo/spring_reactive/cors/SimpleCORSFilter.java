package demo.spring_reactive.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleCORSFilter implements Filter{
static final String ORIGIN = "Origin";
	
	private static Logger logger = LogManager.getLogger();
	
	public SimpleCORSFilter() {
		logger.info("CORS filter is configured.");
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		 	HttpServletRequest request = (HttpServletRequest) req;
		 	HttpServletResponse response = (HttpServletResponse) res;
		 	
		    response.setHeader("Access-Control-Allow-Origin", request.getHeader(ORIGIN));
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			 response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, multipart/form-data " );
			response.setHeader("Access-Control-Allow-Credentials", "true");
			chain.doFilter(req, res);
		  }

		  public void init(FilterConfig filterConfig) {}

		  public void destroy() {}
}
