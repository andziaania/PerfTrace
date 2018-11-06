package boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */

@RestController
public class RestApiController {

  @RequestMapping("/api/hi")
  public String hi() {
    return "Hello world!";
  }

  @RequestMapping("/api/cookie")
  public String getCookie(HttpServletResponse response) {
    response.addCookie(new Cookie("id", new Integer(new Random().nextInt()).toString()));
    return "my-page";
  }
}
