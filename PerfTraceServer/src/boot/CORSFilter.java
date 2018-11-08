package boot;

/**
 * Cross-origin resource sharing (CORS) is a mechanism that allows restricted resources on a web page to be requested
 * from another domain outside the domain from which the first resource was served
 * (source: https://en.wikipedia.org/wiki/Cross-origin_resource_sharing)
 *
 * This project allows calls from a js script that is served:
 * The frontend JavaScript code for a web application served from this project uses XMLHttpRequest to make a request
 * for this project methods.
 * Therefore we allow CORS.
 *
 * The Access-Control-Allow-Origin response header indicates whether the response can be
 * shared with requesting code from the origin given in the request header.
 *
 * @author ania.pawelczyk
 * @since 07.11.2018.
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//    response.setHeader("Access-Control-Max-Age", "3600");
//    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
    chain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void destroy() {
  }

}